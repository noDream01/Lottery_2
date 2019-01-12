package lv.lottery;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    List<T> getAll(Class<T> clazz);

    Optional<T> getById(Long id, Class<T> clazz);

    Long insert(T user);

    void update(T user);

//    Optional<T> getByAssignedId(Long assignedId, Class<T> clazz);
}
