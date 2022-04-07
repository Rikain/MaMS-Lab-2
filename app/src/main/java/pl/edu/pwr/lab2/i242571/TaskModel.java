package pl.edu.pwr.lab2.i242571;

public class TaskModel {
    TaskModel(String title, int type, String description, String dueDate){
        this.title = title;
        this.type = type;
        this.description = description;
        this.status = Status.pending;
        this.dueDate = dueDate;
    }
    TaskModel(){}

    public static final String dateFormat = "dd/mm/yyyy";

    public static class TaskType{
        public static final int todo = 0;
        public static final int email = 1;
        public static final int phone = 2;
        public static final int meeting = 3;

        public static int typeToImage(int status){
            switch (status){
                case email:
                    return R.drawable.ic_email;
                case phone:
                    return R.drawable.ic_phone;
                case meeting:
                    return R.drawable.ic_meeting;
                case todo:
                default:
                    return R.drawable.ic_to_do;
            }
        }
    }

    public static class Status{
        public static final int pending = 0;
        public static final int done = 1;

        public static int statusToImage(int status){
            if(status == TaskModel.Status.done){
                return R.drawable.ic_status_done;
            }else{
                return R.drawable.ic_status_pending;
            }
        }
    }

    private long id;
    private int status;
    private String title, description;
    private int type;
    private String dueDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
