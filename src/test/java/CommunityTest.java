import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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

  @Test
  public void save_insertsObjectIntoDatabase_Community() {
    testCommunity.save();
    assertEquals(true, Community.all().get(0).equals(testCommunity));
  }

  @Test
  public void all_returnsAllInstancesOfCommunity_true() {
    testCommunity.save();
    Community secondCommunity = new Community("Fire Enthusiasts", "Flame on");
    secondCommunity.save();
    assertEquals(true, Community.all().get(0).equals(testCommunity));
    assertEquals(true, Community.all().get(1).equals(secondCommunity));
  }

  @Test
  public void addPerson_addsPersonToCommunity() {
    testCommunity.save();
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    testCommunity.addPerson(testPerson);
    Person savedPerson = testCommunity.getPersons().get(0);
    assertTrue(testPerson.equals(savedPerson));
  }

  @Test
  public void getPersons_returnsAllPersons_List() {
    testCommunity.save();
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    testCommunity.addPerson(testPerson);
    List savedPersons = testCommunity.getPersons();
    assertEquals(savedPersons.size(), 1);
  }

  @Test
  public void delete_deletesCommunity_true() {
    testCommunity.save();
    testCommunity.delete();
    assertEquals(0, Community.all().size());
  }

  @Test
  public void delete_deletesAllPersonsAndCommunitiesAssociations() {
    testCommunity.save();
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    testCommunity.addPerson(testPerson);
    testCommunity.delete();
    assertEquals(0, testPerson.getCommunities().size());
  }

  @Test
  public void removePerson_removesAssociationWithSpecifiedCommunity() {
    testCommunity.save();
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    testCommunity.removePerson(testPerson);
    List savedPersons = testCommunity.getPersons();
    assertEquals(0, savedPersons.size());
  }
}
