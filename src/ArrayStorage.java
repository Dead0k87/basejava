
import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Реализуйте класс ArrayStorage, организовав хранение резюме на основе массива с методами save, get, delete, size, clear, getAll
 * Храните все резюме в начале storage (без дырок в виде null), чтобы не перебирать каждый раз все 10000 элементов
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (resume != null) {
            for (int i = 0; i < size; i++) {
                if (resume.equals(storage[i])) { // duplicates check
                    System.out.println("! Резюме(" + resume + ") уже существует в базе данных и не будет добавлено повторно.");
                    return;
                }
            }
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
                size--;
            }
        }
        storage = Arrays.stream(storage).filter(s -> (s != null)).toArray(Resume[]::new);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        int newArrayIndex = 0;
        Resume[] newArray = new Resume[size];
        for (int i = 0; i < size; i++) {
            //if (storage[i] != null) {
            newArray[newArrayIndex] = storage[i];
            newArrayIndex++;
            //}
        }
        return newArray;
    }

    public int size() {
        return size;
    }
}
