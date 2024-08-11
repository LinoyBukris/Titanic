import java.util.List;

public class Passenger {

    public static final int SURVIVED = 1;



    private String passengerId;
    private int survived;
    private int pclass;
    private String name;
    private String sex;
    private String age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private String embarked;

    public Passenger(String passengerId, int survived, int pclass, String name, String sex, String age, int sibSp, int parch, String ticket, double fare, String cabin, String embarked) {
        this.passengerId = passengerId;
        this.survived = survived;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }


    public String getFormattedName(){
        if (this.name.contains("("))
        this.name.substring(0, this.name.indexOf(('(')));

        List<String> listPartsName = List.of(this.name.split(" "));
        String formattedName = listPartsName.get(listPartsName.size()-1).substring(0,listPartsName.get(listPartsName.size()-1).length()-1) + " " + listPartsName.get(0).substring(1);
        return formattedName;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getSurvived() {
        return survived;
    }

    public int getPclass() {
        return pclass;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        try {
            if (age == null || age.isEmpty()) {
                return -1;
            }
            // אם הגיל יכול להיות עם נקודה עשרונית, השתמש בDouble.parseDouble
            double parsedAge = Double.parseDouble(age);
            if (parsedAge < 0) {
                return -1;
            }
            // המרת הגיל ל-int. אם יש צורך לעגל, השתמש ב-Math.round
            return (int) Math.round(parsedAge);
        } catch (NumberFormatException e) {
            // במקרה של שגיאת המרה, החזר -1
            return -1;
        }
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public double getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    @Override
    public String toString() {
        return "passengerId: " + passengerId +
                ", survived: " + survived +
                ", pclass: " + pclass +
                ", name: " + getFormattedName() +
                ", sex: " + sex +
                ", age: " + age +
                ", sibSp: " + sibSp +
                ", parch: " + parch +
                ", ticket: " + ticket +
                ", fare: " + fare +
                ", cabin: " + cabin +
                ", embarked: " + embarked;
    }
    public String toCSVString() {
        return passengerId + "," +
                survived + "," +
                pclass + "," +
                "\"" + getFormattedName() + "\"," +
                sex + "," +
                (age != null ? age : "") + "," +
                sibSp + "," +
                parch + "," +
                ticket + "," +
                fare + "," +
                (cabin != null ? cabin : "") + "," +
                embarked;
    }
}
