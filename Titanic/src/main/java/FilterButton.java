import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterButton extends JButton {

    private List<Passenger> filterList;
    private ManageScreen manageScreen;
    private int num;

    public FilterButton(ManageScreen manageScreen){
        this.manageScreen = manageScreen;
        this.num = 0;
        restart();
        actionListener();

        this.setText("start");
        this.setBounds(this.manageScreen.getSibSpTextField().getX() + this.manageScreen.getSibSpTextField().getWidth() + 10*Constants.MARGIN_FROM_LEFT, this.manageScreen.getSibSpTextField().getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.manageScreen.add(this);




    }
    public void restart(){
        this.filterList = new ArrayList<>(this.manageScreen.getPassengers());

    }

    public void pClass(){
        int selectedIndex = this.manageScreen.getpClassComboBox().getSelectedIndex();
        if (selectedIndex > 0) {
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getPclass() != selectedIndex) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }
    public void id() {
        int max = this.manageScreen.getMaxTextField().getText().isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(this.manageScreen.getMaxTextField().getText());
        int min = this.manageScreen.getMinTextField().getText().isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(this.manageScreen.getMinTextField().getText());

        for (int i = 0; i < filterList.size(); i++) {
            int passengerId = Integer.parseInt(this.filterList.get(i).getPassengerId());
            if (passengerId < min || passengerId > max) {
                filterList.remove(i);
                i--;
            }
        }
    }



    public void name(){
        String string = this.manageScreen.getNameTextField().getText();
        for (int i = 0; i < filterList.size(); i++) {
            if (!filterList.get(i).getFormattedName().contains(string)) {
                filterList.remove(i);
                i--;
            }
        }
    }

    public void sex(){
        int selectedIndex = this.manageScreen.getpSexComboBox().getSelectedIndex();
        if (selectedIndex > 0) {
            for (int i = 0; i < filterList.size(); i++) {
                if (!filterList.get(i).getSex().equalsIgnoreCase(Constants.PASSENGER_SEX_OPTIONS[selectedIndex])) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }

    public void sibSp() {
        if (!this.manageScreen.getSibSpTextField().getText().isEmpty()) {
            int num;
            try {
                num = Integer.parseInt(this.manageScreen.getSibSpTextField().getText());
            } catch (NumberFormatException e) {
                return;
            }

            for (int i = 0; i < filterList.size(); i++) {
                if (num != this.filterList.get(i).getSibSp()) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }

    public void parch() {
        if (!this.manageScreen.getParchTextField().getText().isEmpty()) {
            int num;
            try {
                num = Integer.parseInt(this.manageScreen.getParchTextField().getText());
            } catch (NumberFormatException e) {
                return;
            }

            for (int i = 0; i < filterList.size(); i++) {
                if (num != this.filterList.get(i).getParch()) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }

    public void ticket() {
        String text = this.manageScreen.getTicketTextField().getText();
        if (!text.isEmpty()) {
            for (int i = 0; i < filterList.size(); i++) {
                if (!this.filterList.get(i).getTicket().contains(text)) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }

    public void cabin() {
        String text = this.manageScreen.getCabinTextField().getText();
        if (!text.isEmpty()) {
            for (int i = 0; i < filterList.size(); i++) {
                if (!this.filterList.get(i).getCabin().contains(text)) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }



    public void fare() {
        double max = this.manageScreen.getMaxTextField2().getText().isEmpty() ? Integer.MAX_VALUE : Double.parseDouble(this.manageScreen.getMaxTextField2().getText());
        double min = this.manageScreen.getMinTextField2().getText().isEmpty() ? Integer.MIN_VALUE : Double.parseDouble(this.manageScreen.getMinTextField2().getText());

        for (int i = 0; i < filterList.size(); i++) {
            double fare = this.filterList.get(i).getFare();
            if (fare < min || fare > max) {
                filterList.remove(i);
                i--;
            }
        }
    }

    public void embarked() {
        int selectedIndex = this.manageScreen.getEmbarkedComboBox().getSelectedIndex();

        if (selectedIndex > 0 && selectedIndex < Constants.PASSENGER_EMBARKED_OPTIONS.length) {
            String selectedOption = Constants.PASSENGER_EMBARKED_OPTIONS[selectedIndex];
            System.out.println("Selected embarked option: " + selectedOption);

            for (int i = 0; i < filterList.size(); i++) {
                String passengerEmbarked = filterList.get(i).getEmbarked();
                System.out.println("Checking passenger embarked: " + passengerEmbarked);

                if (!passengerEmbarked.equalsIgnoreCase(selectedOption)) {
                    filterList.remove(i);
                    i--;
                }
            }
        }
    }

    public void write(List<Passenger> list, int num) {
        try {
            String filePath = String.valueOf(num) + ".csv";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked\n");


            List<Passenger> sortedList = list.stream()
                    .sorted(Comparator.comparing(Passenger::getFormattedName))
                    .collect(Collectors.toList());

            for (Passenger passenger : sortedList) {
                writer.write(passenger.toCSVString() + "\n");
            }

            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







    public void actionListener(){
        this.addActionListener((event) -> {

            num++;
            restart();
            pClass();
            name();
            id();
            sex();
            sibSp();
            parch();
            fare();
            ticket();
            cabin();
            embarked();

            this.manageScreen.getStatisticsButton().setCurrentList(this.filterList);

            this.manageScreen.setListAsTextOnScreen(this.filterList);
            write(this.filterList, this.num);
        });
    }


    public List<Passenger> getFilterList() {
        return filterList;
    }
}
