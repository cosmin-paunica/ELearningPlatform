package services;

public interface Loader {
    void loadUsers(AppService service);
    void loadSubjects(AppService service);
    void loadClasses(AppService service);
    void loadEnrollments(AppService service);

    default void loadData(AppService service) {
        loadUsers(service);
        loadSubjects(service);
        loadClasses(service);
        loadEnrollments(service);
    }
}
