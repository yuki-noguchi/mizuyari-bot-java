package bot.mizuyari.mizuyaribot.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "LINE_USER")
public class User extends BaseEntity {

  @Id
  @Column(name = "LINE_USER_ID", nullable = false)
  private String userId;

  @Column(name = "STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<Watering> waterings;

  public User(String userId) {
    this.userId = userId;
    this.status = UserStatus.STANDBY;
  }

  public String getUserId() {
    return userId;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void startRegistrationSession() {
    this.status = UserStatus.PROCESSING;
    waterings.add(new Watering(this));
  }

  public void startUpdateSession(String plantName) {
    this.status = UserStatus.PROCESSING;
    waterings = waterings.stream().filter(watering -> watering.getPlantName().equals(plantName))
        .map(watering -> {
          watering.startUpdate();;
          return watering;
        }).toList();
  }

  public void cancelRegistrationSession() {
    this.status = UserStatus.STANDBY;
    waterings.stream().filter(watering -> watering.getStatus() != WateringStatus.COMPLETED)
        .map(Watering::getPlantName).forEach(this::deleteWatering);
  }

  public void registerWatering(String plantName) {
    waterings = waterings.stream()
        .filter(watering -> watering.getStatus() == WateringStatus.WAITING_FOR_PLANT_NAME)
        .map(watering -> {
          watering.setPlantName(plantName);
          return watering;
        }).toList();
  }

  public void setWateringFrequencyInDays(int frequencyInDays) {
    waterings = waterings.stream()
        .filter(watering -> watering.getStatus() == WateringStatus.WAITING_FOR_FREQUENCY_IN_DAYS)
        .map(watering -> {
          watering.setFrequencyInDays(frequencyInDays);
          return watering;
        }).toList();
  }

  public void setWateringNextDate(LocalDate nextDate) {
    waterings = waterings.stream()
        .filter(watering -> watering.getStatus() == WateringStatus.WAITING_FOR_FREQUENCY_IN_DAYS)
        .map(watering -> {
          watering.setNextDate(nextDate);
          return watering;
        }).toList();
    this.status = UserStatus.STANDBY;
  }

  public void deleteWatering(String plantName) {
    waterings =
        waterings.stream().filter(watering -> !watering.getPlantName().equals(plantName)).toList();
  }

  public List<Watering> getWaterings() {
    return Collections.unmodifiableList(this.waterings);
  }
}
