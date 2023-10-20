import java.util.*;

public class Main {
    static Random rnd = new Random();
    static Formatter fmt = new Formatter();
    static List<Listener> listenersList = new ArrayList<>();
    static int idNextListener;
    static Scanner in = new Scanner(System.in);
    static boolean flag;


    public static void main(String[] args) {
        int op = 0;
        boolean flag;
        Init();
        do {
            System.out.println("--------------------------------------------");
            System.out.println("            MENU GAME OF THE BOX");
            System.out.println("--------------------------------------------");
            System.out.println("\t\t1-Create listeners");
            System.out.println("\t\t2-Edit listeners");
            System.out.println("\t\t3-Delete listeners");
            System.out.println("\t\t4-View listeners data");
            System.out.println("\t\t5-View listener ranking");
            System.out.println("\t\t6-Play");
            System.out.println("0-Exit");
            System.out.print("Option:");

            do {
                try {
                    op = in.nextInt();
                    flag = false;
                } catch (Exception e) {
                    // aceita apenas inteiro
                    System.out.println("Invalid option! Enter numbers only!");
                    in = new Scanner(System.in);
                    flag = true;
                }
            }
            while (flag);
            calculateRanking();

            switch (op) {
                case 0 -> {
                    return;
                }
                case 1 -> createListeners();
                case 2 -> editListeners();
                case 3 -> deleteListeners();
                case 4 -> viewDataListeners();
                case 5 -> viewRankingListeners();
                case 6 -> play();
                default -> System.out.println("Invalid option");
            }
        } while (true);
    }

    private static void createListeners() {
        in = new Scanner(System.in);
        System.out.println("What is the listener's first name?");
        String name = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("What is the listener's locality?");
        String locality = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("What is the listener's phone number?");

        int phone = 0;
        do {
            try {
                Scanner in = new Scanner(System.in);
                phone = in.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid number entered." + e.getMessage());
                in = new Scanner(System.in);
                flag = true;

            }
        }
        while (flag);

        listenersList.add(new Listener(idNextListener, name, locality, phone));
        idNextListener++;
        System.out.println("Listener added successfully!");
    }

    private static void editListeners() {
        writeListenersList(listenersList);
        System.out.println("What is the Listener ID to edit?");

        int id = 0;
        do {
            try {
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid option! Enter numbers only.");
                in = new Scanner(System.in);
                flag = true;
            }
        }
        while (flag);
        for (int i = 0; i < listenersList.size(); i++) {
            if (listenersList.get(i).getId() == id) {
                in = new Scanner(System.in);
                System.out.print("Change name:");
                String name = in.nextLine();
                listenersList.get(i).setName(name);

                System.out.print("Change Location:");
                String locality = in.nextLine();
                listenersList.get(i).setLocality(locality);

                in = new Scanner(System.in);
                System.out.print("Change phone:");
                int phone = in.nextInt();
                listenersList.get(i).setPhone(phone);

                System.out.println("Listener Changed Successfully!");
                writeListenersList(listenersList);
                return;
            }
        }
    }

    private static void deleteListeners() {
        writeListenersList(listenersList);
        System.out.println("What is the Listener ID to delete?");
        int id = in.nextInt();

        for (int i = 0; i < listenersList.size(); i++) {
            if (listenersList.get(i).getId() == id) {
                listenersList.remove(i);
                System.out.println("Listener removed successfully");
                return;
            }
        }
        System.out.println("Error deleting listener");
    }

    private static void viewDataListeners() {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to see everyone listening? (Type Y-yes or N-no)");
        String op = in.nextLine();
        String y = "y";
        String Y = "Y";
        String n = "n";
        String N = "N";
        if (op.equals(Y) || op.equals(y)) {
            fmt = new Formatter();
            writeListenersList(listenersList);
        } else if (op.equals(N) || op.equals(n)) {
            System.out.println("Enter the Listener ID");
            fmt = new Formatter();

            int id = 0;
            do {
                try {
                    in = new Scanner(System.in);
                    id = in.nextInt();
                    flag = false;
                } catch (Exception e) {
                    System.out.println("Invalid option! Enter numbers only.");
                    in = new Scanner(System.in);
                    flag = true;
                }
            }
            while (flag);

            System.out.println(listenersList.get(0).writeHeaderData());
            Boolean valid = true;
            for (Listener listenerList : listenersList) {
                if (listenerList.getId() == id) {
                    System.out.println(listenerList.writeTableData());
                    valid = false;
                }
            }
            if (valid) {
                System.out.println("There are no listeners with this id!");
            }
        } else {
            System.out.println("Invalid option");
        }
    }

    private static void viewRankingListeners() {
        writeListenersListRanking(listenersList);
    }

    private static void calculateRanking() {
        int rank = 1;
        // a variavel max armazena o valor maximo de vitorias de todos os ouvintes na listaOuvintes
        int max = listenersList.stream().mapToInt(Listener::getVictories).max().orElse(0);

        int counter;
        for (int j = max; j > 0; j--) {
            counter = 0;
            for (Listener listenerList : listenersList) {
                if (j == listenerList.getVictories()) {
                    counter++;
                    if (counter == 1) {
                        listenerList.setRanking(rank);
                        rank++;
                    } else {
                        rank++;
                        listenerList.setRanking(rank - counter);
                    }
                }
            }
        }
        for (int i = 0; i < listenersList.size(); i++) {
            for (int j = 0; j < listenersList.size(); j++) {
                if (i != j) {
                    if (listenersList.get(i).getRanking() == listenersList.get(j).getRanking() && listenersList.get(i).getRanking() != 0) {
                        if (listenersList.get(i).getPlays() <= listenersList.get(j).getPlays()) {
                            listenersList.get(j).setRanking(listenersList.get(i).getRanking() + 1);
                        } else
                            listenersList.get(i).setRanking(listenersList.get(j).getRanking() + 1);
                    }
                }
            }
        }
    }

    private static void play() {

        System.out.println("-------New Game--------");
        int MARGIN = 150;
        int iniValue = rnd.nextInt(1, 9849);
        int finValue = (iniValue + MARGIN);
        int numPlayers = rnd.nextInt(2, (listenersList.size() + 1));
        System.out.print("--> The weight of the box is between: " + iniValue + " Kg and " + finValue + " Kg");
        System.out.println(" || Number of players in the competition: " + numPlayers);

        int weightBox = rnd.nextInt(iniValue, finValue);

        int dif;
        int winner = 0;
        int temp = 150;

        ArrayList<Listener> players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {

            int pos = rnd.nextInt(0, listenersList.size());
            boolean exists = false;

            for (Listener o : players) {
                while (o.equals(listenersList.get(pos))) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                i--;
                exists = false;

            } else {
                players.add(listenersList.get(pos));

                listenersList.get(pos).setPlays(listenersList.get(pos).getPlays() + 1);
            }
        }

        for (Listener u : players) {
            int bet = rnd.nextInt(iniValue, finValue);
            System.out.println("The Listener " + u.getName() + " with ID " + u.getId() + " bet: " + bet + " Kg");

            dif = weightBox - bet;
            //System.out.print("  | Dif: " + Math.abs(dif));

            if (Math.abs(dif) <= temp) {
                temp = Math.abs(dif);
                winner = getIdPosition(u.getId());
            }
        }

        System.out.print("Weight Box: " + weightBox + " Kg");
        System.out.println(" --> The Winner is "+ listenersList.get(winner).getName()+" with ID:"+ listenersList.get(winner).getId()) ;
        listenersList.get(winner).setVictories(listenersList.get(winner).getVictories() + 1);
        players.clear();
    }

    private static void writeListenersList(List<Listener> listenerList) {

        System.out.println(listenerList.get(0).writeHeaderData());
        if (listenerList.size() > 0) {
            for (int i = 0; i < Main.listenersList.size(); i++) {
                System.out.println(Main.listenersList.get(i).writeTableData() + "");
            }
        }
    }

    private static void writeListenersListRanking(List<Listener> listenerList) {

        System.out.println(listenerList.get(0).writeHeaderDataRanking());
        int counter = 1;
        if (listenerList.size() > 0) {
            for (int i = 0; i < listenerList.size(); i++) {
                for (int j = 0; j < listenerList.size(); j++) {
                    if (listenerList.get(i).getRanking() == counter) {
                        System.out.println(listenerList.get(i).writeTableDataRanking() + "");
                        i = 0;
                        counter++;
                    } else if (counter > listenerList.size()) {
                        return;
                    }
                }
            }
        }
    }

    private static int getIdPosition(int number) {
        for (int i = 0; i < listenersList.size(); i++) {
            if (number == listenersList.get(i).getId())
                return i;
        }
        return -1;
    }

    private static void Init() {
        listenersList.add(new Listener(1, "Manuel", "Porto", 911234564, 0, 0, 0));           //0
        listenersList.add(new Listener(2, "Marias", "Funchal", 964151237, 0, 0, 0));         //1
        listenersList.add(new Listener(3, "Filipa", "Bobadela", 932161231, 0, 0, 0));        //2
        listenersList.add(new Listener(4, "Filipe", "Vieira", 964511232, 0, 0, 0));         //3
        listenersList.add(new Listener(5, "Angela", "Vieira", 964511232, 0, 0, 0));          //4
        listenersList.add(new Listener(6, "Julieta", "Romao", 964151237, 0, 0, 0));         //5
        idNextListener = 7;
    }
}