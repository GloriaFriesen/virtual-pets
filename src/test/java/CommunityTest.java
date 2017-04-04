import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CommunityTest {
  Community testCommunity = new Community("Water Enthusiasts", "Lovers of all things water monsters.");

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void community_instantiatesCorrectly_true() {
    Community testCommunity = new Community("Water Enthusiasts", "Lovers of all things water monsters.");
    assertEquals(true, testCommunity instanceof Community);
  }

  @Test
  public void getName_communityInstantiatesWithName_String() {
    Community testCommunity = new Community("Water Enthusiasts", "Lovers of all things water monsters.");
    assertEquals("Water Enthusiasts", testCommunity.getName());
  }

  @Test
  public void getDescription_communityInstantiatesWithDescription_String() {
    Community testCommunity = new Community("Water Enthusiasts", "Lovers of all things water monsters.");
    assertEquals("Lovers of all things water monsters.", testCommunity.getDescription());
  }

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreTheSame_true() {
    Community communityTwo = new Community("Water Enthusiasts", "Lovers of all things water monsters.");
    assertTrue(testCommunity.equals(communityTwo));
  }
}
