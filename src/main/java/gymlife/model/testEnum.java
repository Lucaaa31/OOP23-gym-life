package gymlife.model;

public class testEnum {
    public static void main(String[] args) {
        Encounters encounter = Encounters.PUSHER;
        System.out.println(encounter.name());
        System.out.println(Encounters.getRandomEncounter());
        System.out.println(encounter.name().equals("GYM_BRO"));
        for (int i = 0; i < 10; i++) {
            System.out.println(Encounters.getRandomEncounter());
        }
    }
}
