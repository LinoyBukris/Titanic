import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticsButton extends JButton {

    private ManageScreen manageScreen;
    private List<Passenger> currentList;

    public StatisticsButton(ManageScreen manageScreen) {
        this.manageScreen = manageScreen;
        this.currentList = this.manageScreen.getPassengers();

        actionListener();

        this.setText("Create a statistics file");
        this.setBounds(this.manageScreen.getFilterButton().getX() + this.manageScreen.getFilterButton().getWidth() + 10*Constants.MARGIN_FROM_LEFT, this.manageScreen.getFilterButton().getY(), 3*Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.manageScreen.add(this);


    }

    public void setCurrentList(List<Passenger> currentList) {
        this.currentList = currentList;
    }

    public void actionListener(){
        this.addActionListener((event) -> {

            writeFile(this.currentList);

        });
    }

    public String percentageCalculator(int all , int partOf){
        if (all != 0) return (partOf*100)/all + "%";
        return 0 + "%";
    }
    public int howManySurvived(List<Passenger> list){
        return (int) list.stream().filter(Passenger -> Passenger.getSurvived()==1).count();
    }

    public void writePClass(BufferedWriter writer, List<Passenger> list) throws IOException {

        writer.write("\nSurvival percentages by class: \n");

        writer.write("first - ");
        List<Passenger> listA = list.stream().filter(Passenger -> Passenger.getPclass()==1).collect(Collectors.toList());
        writer.write(percentageCalculator(listA.size(), howManySurvived(listA)));
        writer.write("\nsecond - ");
        List<Passenger> listB = list.stream().filter(Passenger -> Passenger.getPclass()==2).collect(Collectors.toList());
        writer.write(percentageCalculator(listB.size(), howManySurvived(listB)));
        writer.write("\nthird - ");
        List<Passenger> listC = list.stream().filter(Passenger -> Passenger.getPclass()==3).collect(Collectors.toList());
        writer.write(percentageCalculator(listC.size(), howManySurvived(listC)));
    }

    public void writeSex(BufferedWriter writer, List<Passenger> list) throws IOException {

        writer.write("\n\nSurvival percentages by sex: \n");


        writer.write("male - ");
        List<Passenger> listA = list.stream().filter(Passenger -> Passenger.getSex().equals("male")).collect(Collectors.toList());
        writer.write(percentageCalculator(listA.size(), howManySurvived(listA)));
        writer.write("\nfemale - ");
        List<Passenger> listB = list.stream().filter(Passenger -> Passenger.getSex().equals("female")).collect(Collectors.toList());
        writer.write(percentageCalculator(listB.size(), howManySurvived(listB)));
    }

    public void writeAge(BufferedWriter writer, List<Passenger> list) throws IOException {

        writer.write("\n\nSurvival percentages by class: \n");

        writer.write("0-10 - ");
        List<Passenger> listA = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age >= 0 && age <= 10;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listA.size(), howManySurvived(listA)));

        writer.write("\n11-20 - ");
        List<Passenger> listB = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age > 10 && age <= 20;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listB.size(), howManySurvived(listB)));

        writer.write("\n21-30 - ");
        List<Passenger> listC = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age > 20 && age <= 30;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listC.size(), howManySurvived(listC)));

        writer.write("\n31-40 - ");
        List<Passenger> listD = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age > 30 && age <= 40;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listD.size(), howManySurvived(listD)));

        writer.write("\n41-50 - ");
        List<Passenger> listE = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age > 40 && age <= 50;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listE.size(), howManySurvived(listE)));

        writer.write("\n51+ - ");
        List<Passenger> listF = list.stream().filter(passenger -> {
            int age = passenger.getAge();
            return age > 50;
        }).collect(Collectors.toList());
        writer.write(percentageCalculator(listF.size(), howManySurvived(listF)));
    }
    public void writeFamily(BufferedWriter writer, List<Passenger> list) throws IOException {
        writer.write("\n\nSurvival percentages by family presence: \n");

        // נוסעים עם לפחות קרוב משפחה אחד על הסיפון
        List<Passenger> withFamily = list.stream().filter(passenger -> {
            return (passenger.getSibSp() > 0 || passenger.getParch() > 0);
        }).collect(Collectors.toList());
        writer.write("With family - ");
        writer.write(percentageCalculator(withFamily.size(), howManySurvived(withFamily)));

        // נוסעים ללא קרוב משפחה על הסיפון
        List<Passenger> withoutFamily = list.stream().filter(passenger -> {
            return (passenger.getSibSp() == 0 && passenger.getParch() == 0);
        }).collect(Collectors.toList());
        writer.write("\nWithout family - ");
        writer.write(percentageCalculator(withoutFamily.size(), howManySurvived(withoutFamily)));
    }
    public void writeTicketPrice(BufferedWriter writer, List<Passenger> list) throws IOException {
        writer.write("\n\nSurvival percentages by ticket price: \n");

        // נוסעים ששילמו פחות מ-10 פאונד
        List<Passenger> lessThan10 = list.stream().filter(passenger -> passenger.getFare() < 10).collect(Collectors.toList());
        writer.write("Less than 10 pounds - ");
        writer.write(percentageCalculator(lessThan10.size(), howManySurvived(lessThan10)));

        // נוסעים ששילמו 11-30 פאונד
        List<Passenger> between11And30 = list.stream().filter(passenger -> passenger.getFare() >= 11 && passenger.getFare() <= 30).collect(Collectors.toList());
        writer.write("\n11-30 pounds - ");
        writer.write(percentageCalculator(between11And30.size(), howManySurvived(between11And30)));

        // נוסעים ששילמו יותר מ-30 פאונד
        List<Passenger> moreThan30 = list.stream().filter(passenger -> passenger.getFare() > 30).collect(Collectors.toList());
        writer.write("\nMore than 30 pounds - ");
        writer.write(percentageCalculator(moreThan30.size(), howManySurvived(moreThan30)));
    }
    public void writeEmbarked(BufferedWriter writer, List<Passenger> list) throws IOException {
        writer.write("\n\nSurvival percentages by port of embarkation: \n");

        Set<String> ports = list.stream()
                .map(Passenger::getEmbarked)
                .filter(Objects::nonNull)
                .filter(port -> !port.trim().isEmpty())
                .collect(Collectors.toSet());

        boolean hasPrinted = false;

        for (String port : ports) {
            List<Passenger> fromPort = list.stream()
                    .filter(passenger -> port.equals(passenger.getEmbarked()))
                    .collect(Collectors.toList());

            String percentage = percentageCalculator(fromPort.size(), howManySurvived(fromPort));

            if (!percentage.equals("100%")) {
                writer.write(port + " - ");
                writer.write(percentage);
                writer.write("\n");
                hasPrinted = true;
            }
        }

        if (!hasPrinted) {
            writer.write("No data available.\n");
        }
    }




    public void writeFile(List<Passenger> list) {
        try {
            String filePath = "Statistics.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("Survival rates: \n ");
            writePClass(writer, list);
            writeSex(writer, list);
            writeAge(writer, list);
            writeFamily(writer, list);
            writeTicketPrice(writer,list);
            writeEmbarked(writer, list);



            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







}
