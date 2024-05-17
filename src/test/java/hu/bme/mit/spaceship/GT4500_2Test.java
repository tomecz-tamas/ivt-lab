package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500_2Test {

  private GT4500 ship;
  private TorpedoStore primary, secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_PrimaryEmpty_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_SecondaryEmpty_Twice_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false).thenReturn(false);
    when(primary.fire(1)).thenReturn(true).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_PrimaryEmpty_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Fail(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false).thenReturn(true);
    when(primary.fire(1)).thenReturn(true).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(false, result2);
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_SecondaryEmpty_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Empty_Fail(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }
}
