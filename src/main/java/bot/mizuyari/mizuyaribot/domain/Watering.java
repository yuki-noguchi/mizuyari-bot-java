package bot.mizuyari.mizuyaribot.domain;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "WATERING")
public class Watering extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "WATERING_ID", nullable = false)
  private Long wateringId;

  @ManyToOne
  @JoinColumn(name = "LINE_USER_ID", referencedColumnName = "LINE_USER_ID", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private WateringStatus status;

  @Column(name = "PLANT_NAME", nullable = true)
  private String plantName;

  @Column(name = "FREQUENCY_IN_DAYS", nullable = true)
  private int frequencyInDays;

  @Column(name = "NEXT_DATE", nullable = true)
  private LocalDate nextDate;

  public Watering(User user) {
    this.status = WateringStatus.WAITING_FOR_PLANT_NAME;
    this.user = user;
  }

  public void startUpdate() {
    this.status = WateringStatus.WAITING_FOR_PLANT_NAME;
  }

  public WateringStatus getStatus() {
    return status;
  }

  public String getPlantName() {
    return plantName;
  }

  public int getFrequencyInDays() {
    return frequencyInDays;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
    this.status = WateringStatus.WAITING_FOR_FREQUENCY_IN_DAYS;
  }

  public void setFrequencyInDays(int frequencyInDays) {
    this.frequencyInDays = frequencyInDays;
    this.status = WateringStatus.WAITING_FOR_NEXT_DATE;
  }

  public LocalDate getNextDate() {
    return nextDate;
  }

  public void setNextDate(LocalDate nextDate) {
    this.nextDate = nextDate;
    this.status = WateringStatus.COMPLETED;
  }
}
