package services;

public interface Loader {
    public void loadUsers(AppService service);
    public void loadSubjects(AppService service);
    public void loadClasses(AppService service);
    public void loadEnrollments(AppService service);
    public void loadData(AppService service);
}
