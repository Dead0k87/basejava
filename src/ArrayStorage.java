import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Реализуйте класс ArrayStorage, организовав хранение резюме на основе массива с методами save, get, delete, size, clear, getAll
 * Храните все резюме в начале storage (без дырок в виде null), чтобы не перебирать каждый раз все 10000 элементов
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume resume) {

        if (resume != null) {
            int lastIndex = 0;
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == resume) {
                    System.out.println("Resume you are trying to add(" + resume + ") is already exist");
                    break;
                }
                if (storage[i] == null) {
                    lastIndex = i;
                    break;
                }
            }
            storage[lastIndex] = resume;
        }
        System.out.println("Resume storage: " + storage.toString());
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
            }
        }
        storage = Arrays.stream(storage).filter(s -> (s != null)).toArray(Resume[]::new);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        ArrayList<Resume> resumes = new ArrayList<>();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resumes.add(storage[i]);
            }
        }
        return resumes.toArray(new Resume[resumes.size()]);
    }

    int size() {
        int effectiveSize = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                effectiveSize = i + 1;
                break;
            }
        }
        return effectiveSize;
    }
}
