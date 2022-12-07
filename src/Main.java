import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long youngPeople = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> recruit = persons.stream()
                .filter(person -> (person.getSex().toString().equals("MAN") && person.getAge() >= 18 && person.getAge() < 28))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        List<String> workablePeople = persons.stream()
                .filter(person -> ((person.getEducation().toString().equals("HIGHER") && person.getAge() >= 18 && person.getAge() < 60 && person.getSex().toString().equals("WOMAN"))  ||
                        (person.getEducation().toString().equals("HIGHER") && person.getAge() >= 18 && person.getAge() < 65 && person.getSex().toString().equals("MAN"))))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println(youngPeople);
        System.out.println();
        System.out.println(recruit);
        System.out.println();
        System.out.println(workablePeople);

    }
}
