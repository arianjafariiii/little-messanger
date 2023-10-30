import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Panel.mainPanel();
    }

    public static class UserManager {
        public static ArrayList<User> users = new ArrayList<>();

    }

    public static class User {
        private final String username;
        private String password ;
        private ArrayList<Massage> incomingMassages = new ArrayList<>();
        private ArrayList<Massage> sentMassages = new ArrayList<>();
        private ArrayList<Chat> chats = new ArrayList<>();

        public ArrayList<String> getDashboardUsers() {
            return dashboardUsers;
        }

        public void setDashboardUsers(ArrayList<String> dashboardUsers) {
            this.dashboardUsers = dashboardUsers;
        }

        private ArrayList<String> dashboardUsers = new ArrayList<>();

        public User(String userName, String password) {
            this.username = userName;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public ArrayList<Massage> getIncomingMassages() {
            return incomingMassages;
        }

        public void setIncomingMassages(ArrayList<Massage> incomingMassages) {
            this.incomingMassages = incomingMassages;
        }

        public ArrayList<Massage> getSentMassages() {
            return sentMassages;
        }

        public void setSentMassages(ArrayList<Massage> sentMassages) {
            this.sentMassages = sentMassages;
        }

        public ArrayList<Chat> getChats() {
            return chats;
        }

        public void setChats(ArrayList<Chat> chats) {
            this.chats = chats;
        }
    }

    public static class Massage {
        private String time;
        private String date;
        private String textBody;
        private String destUsername;
        private String originUsername;
        private int massageNumber;
        private int likeDislike = 0;
        private String like;

        public Massage(String time, String date, String textBody, String destUsername, String originUsername) {
            this.time = time;
            this.date = date;
            this.textBody = textBody;
            this.destUsername = destUsername;
            this.originUsername = originUsername;
        }

        @Override
        public String toString() {
            return "--------------------------------------------\n" +
                    "*********" + originUsername + "*********" + '\n' +
                    massageNumber + '\n'+
                     textBody + '\n' +
                    like + '\n'+
                     time + '\n'
                    + date + '\n' +
                    "--------------------------------------------"
                    ;
        }

        public int getMassageNumber() {
            return massageNumber;
        }

        public void setMassageNumber(int massageNumber) {
            this.massageNumber = massageNumber;
        }

        public String getTextBody() {
            return textBody;
        }

        public void setTextBody(String textBody) {
            this.textBody = textBody;
        }

        public String getOriginUsername() {
            return originUsername;
        }

        public void setOriginUsername(String originUsername) {
            this.originUsername = originUsername;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }
    }

    public static class Chat {
        private String person1 ;
        private String person2 ;
        private ArrayList<Massage> chats = new ArrayList<>();

        public Chat(String person1, String person2) {
            this.person1 = person1;
            this.person2 = person2;
        }

        public String getPerson1() {
            return person1;
        }

        public void setPerson1(String person1) {
            this.person1 = person1;
        }

        public String getPerson2() {
            return person2;
        }

        public void setPerson2(String person2) {
            this.person2 = person2;
        }

        public ArrayList<Massage> getChats() {
            return chats;
        }

        public void setChats(ArrayList<Massage> chats) {
            this.chats = chats;
        }

    }

    public static class UserPanel {
        static int choice = 0;
        static Scanner sc = new Scanner(System.in);
        public static void mainPanel(String username){
            System.out.println("Enter a number...: \n" +
                    "1) Search username\n" +
                    "2) See dashboard\n" +
                    "3) Exit\n" +
                    "******************************************************************************************************** ");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    searchUsername(username);
                    break;
                case 2:
                    seeDashboard(username);
                    chooseChat(username);
                    break;
                case 3:
                    break;

            }

        }
        public static void seeDashboard(String username){
            try {
                checkDashboard(username);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    for (int j = 0; j < UserManager.users.get(j).getDashboardUsers().size(); j++) {
                        int k = j+1;
                        System.out.println(k +")  " + UserManager.users.get(i).getDashboardUsers().get(j));
                    }
                }
            }




        }
        public static void chooseChat( String username ){
            System.out.println("Enter a number");
            int choice = sc.nextInt();
            choice --;
            int l=0;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if ((UserManager.users.get(i).getChats().get(j).getPerson1().equals(username) &&
                                UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                        (UserManager.users.get(i).getDashboardUsers().get(choice)) ||
                                UserManager.users.get(i).getChats().get(j).getPerson1().equals
                                        ((UserManager.users.get(i).getDashboardUsers().get(choice))) &&
                                        UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                                (username))){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());

                            }



                        }
                    }
                }
                l = i;
            }
            ChatPanel.mainPanel(username, UserManager.users.get(l).getDashboardUsers().get(choice) );
            mainPanel(username);

        }
        public static void searchUsername(String username){
            System.out.println("Enter username please");
            String desUsername = sc.next();
            createChat(username, desUsername);
            mainPanel(username);
        }
        public static void createChat (String username, String desUsername){
            int count = 0;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    for (int j = 0; j < UserManager.users.get(i).getDashboardUsers().size(); j++) {
                        if ((UserManager.users.get(i).getDashboardUsers().get(j).equals(desUsername))){
                            count ++;
                        }
                    }
                }
            }

            if (count == 0){
                Chat chat = new Chat(username, desUsername);
                Chat chat1 = new Chat(desUsername, username);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getUsername().equals(username)) {

                        UserManager.users.get(i).getChats().add(chat);
                        UserManager.users.get(i).getDashboardUsers().add(desUsername);
                    }

                }
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if(UserManager.users.get(i).getUsername().equals(desUsername) ){
                        UserManager.users.get(i).getChats().add(chat1);
                        UserManager.users.get(i).getDashboardUsers().add(username);

                    }

                }

            }
            else {
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getUsername().equals(username)) {
                        for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                            if ((UserManager.users.get(i).getChats().get(j).getPerson1().equals(username) &&
                                    UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                            ((UserManager.users.get(i).getDashboardUsers().get(choice))) ||
                                    UserManager.users.get(i).getChats().get(j).getPerson1().equals
                                            ((UserManager.users.get(i).getDashboardUsers().get(choice))) &&
                                            UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                                    (username))) {
                                for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                    System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());

                                }


                            }
                        }
                    }
                    ChatPanel.mainPanel(username, UserManager.users.get(i).getDashboardUsers().get(choice));
                }
            }
            ChatPanel.mainPanel(username, desUsername);


        }
        public static void searchUsername(String originUsername, String destUsername){
            int count = 0;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(originUsername)){
                    for (int j = 0; j < UserManager.users.get(i).getDashboardUsers().size(); j++) {
                        if (UserManager.users.get(i).getDashboardUsers().get(j).equals(destUsername))
                        count++;
                    }
                }

            }
            if (count == 0);


        }
        public static void checkUsername(String username) throws NotFoundException {
            int count = 0;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if(UserManager.users.get(i).getUsername().equals(username));
                count ++;
            }
            if (count ==0){
                throw new NotFoundException("username not found");
            }

        }

        public static void checkDashboard(String username) throws NullPointerException {
            for (int i = 0; i < UserManager.users.size(); i++) {
                if(UserManager.users.get(i).getUsername().equals(username)){
                    if (UserManager.users.get(i).getDashboardUsers().size() == 0 ){
                        throw new NullPointerException("There is no chat here");
                    }
                }

            }

        }
    }

    public static class Panel {
        static Scanner sc = new Scanner(System.in);
        static int choice = 0;
        public static void mainPanel(){
            System.out.println("Enter a number : \n" +
                    "1) Sign up\n" +
                    "2) Login\n" +
                    "3) Exit\n" +
                    "*******************************************************************************************************");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    signup();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    break;

            }


        }
        public static void signup(){

            System.out.println("Enter your username");
            String username = sc.next();
            try{
                checkUsername(username);
                checkUsernameRepeat(username);
            }
            catch (DuplicatedUserException | StringFormatException ex){
                System.out.println(ex.getMessage());
                signup();

            }
            System.out.println("Enter your password");
            String password = sc.next();
            try{
                checkPasswordNumber(password);
                checkPasswordLength(password);
                //checkPasswordEasyPass(password);
            }
            catch(EaesyPasswordException | NumberFormatException exx){
                System.out.println(exx.getMessage());
                signup();
            }
            User user = new User(username, password);
            UserManager.users.add(user);
            mainPanel();


        }
        public static void signIn(){
            System.out.println("Enter your username please : ");
            String username = sc.next();
            try{
                checkUsername(username);

            }
            catch(StringFormatException e){
                System.out.println(e.getMessage());
                signIn();

            }
            System.out.println("Enter your password please : ");
            String password = sc.next();
            try{
                checkPasswordNumber(password);
                checkPasswordLength(password);

            } catch (NumberFormatException | EaesyPasswordException e) {
                System.out.println(e.getMessage());

            }
            try {
                checkInformation(username, password);
            } catch (InvalidInfoException e) {
                e.getMessage();
                signIn();
            }
            UserPanel.mainPanel(username);
            mainPanel();


        }
        public static void checkInformation(String username, String password) throws InvalidInfoException {
            int count = 0;
            int countt =0 ;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    count ++;
                }
            }
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username) && UserManager.users.get(i).getPassword().equals(password))
                {
                    countt ++;
                }
            }
            if (count == 0 || countt ==0){
                throw new InvalidInfoException(" Username or password is wrong");
            }

        }
        public static void checkUsername(String username) throws StringFormatException {
            for (int i = 0; i < username.toCharArray().length; i++) {
                if ((int)username.toCharArray()[i]<65 || (int)username.toCharArray()[i]>122 || ((int)username.toCharArray()[i]>90 && (int)username.toCharArray()[i]<97)){
                        throw new StringFormatException("Just capital and small english characters ");

                }

            }
        }
        public static void checkPasswordNumber (String password) throws NumberFormatException {
            for (int i = 0; i < password.toCharArray().length; i++) {
                if (password.toCharArray()[i]<48 || password.toCharArray()[i]>57){
                    throw new NumberFormatException("Just enter numbers for password.");
                }

            }
        }
        public static void checkPasswordLength(String password) throws EaesyPasswordException {
            if (password.toCharArray().length<8){
                throw new EaesyPasswordException("Your password is so easy .");
            }
        }
        public static void checkPasswordEasyPass(String password) throws EaesyPasswordException{
            int count = 0;
            int countt = 0;
            int counttt = 0;
            if (password.toCharArray()[2] > password.toCharArray()[1]){
                for (int i = 0; i < password.toCharArray().length-1; i++) {
                    if (password.toCharArray()[i+1] < password.toCharArray()[i]){
                        count ++;
                    }
                }
            }
            else if (password.toCharArray()[2] < password.toCharArray()[1]){
                for (int i = 0; i < password.toCharArray().length-1; i++) {
                    if (password.toCharArray()[i+1] > password.toCharArray()[i]){
                        countt ++;
                    }

                }
            }
            for (int i = 0; i < password.toCharArray().length-1; i++) {
                for (int j = i+1; j < password.toCharArray().length; j++) {
                    if (password.toCharArray()[i] == password.toCharArray()[j]){
                        counttt ++ ;
                    }

                }
            }
            if (count == 0 || countt ==0 || counttt>4){
                throw new EaesyPasswordException("Your password is so easy");
            }

        }
        public static void checkUsernameRepeat(String username) throws DuplicatedUserException {
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    throw new DuplicatedUserException("This username is used . ");
                }

            }
        }
    }

    public static class ChatPanel {
        static Scanner sc = new Scanner(System.in);
        static int choice = 0;
        public static void mainPanel(String username, String destUsername){
            System.out.println("Enter a number : \n" +
                    "1) Write and send massage\n" +
                    "2) Like and dislike massages\n" +
                    "3) Edit massages\n" +
                    "4) One side or both side delete\n" +
                    "5) Exit");
            choice = sc.nextInt();
            switch (choice){
            case 1:
                sendMassage(username, destUsername);
                break;
            case 2:
                likeDislike(username, destUsername);
                break;
            case 3:
                editMassage(username, destUsername);
                break;
            case 4:
                bothSideDelete(username, destUsername);
                break;
            case 5:
                break;



            }


        }
        public static void sendMassage(String username , String destUsername){
            Date date = new Date();
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM:dd:yyyy");
            String dateStr = dateFormatter.format(date);
            String timeStr = timeFormatter.format(date);
            System.out.println("Enter your text and if you want to finish enter 0");
            String text ;
            do {
                System.out.println("Enter text...:");
                text = sc.next();
                try {
                    checkSpam(text, username, destUsername);

                }
                catch (NullPointerException exx){
                    System.out.println(exx.toString());
                    sendMassage(username, destUsername);

                }
                if (text.equals("0")) break;
                Massage massage = new Massage(timeStr, dateStr , text, destUsername, username);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getUsername().equals(username)){
                        for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                            if ((UserManager.users.get(i).getChats().get(j).getPerson1().equals(username) &&
                                    UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername))
                            ||(UserManager.users.get(i).getChats().get(j).getPerson2().equals(username) &&
                                    UserManager.users.get(i).getChats().get(j).getPerson1().equals(username))){
                                UserManager.users.get(i).getChats().get(j).getChats().add(massage);
                                massage.setMassageNumber(UserManager.users.get(i).getChats().get(j).getChats().size());

                            }

                        }
                    }


                }
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getUsername().equals(destUsername)){
                        for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                            if ((UserManager.users.get(i).getChats().get(j).getPerson1().equals(username) &&
                                    UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername))
                                    ||(UserManager.users.get(i).getChats().get(j).getPerson2().equals(username) &&
                                    UserManager.users.get(i).getChats().get(j).getPerson1().equals(username))){
                                UserManager.users.get(i).getChats().get(j).getChats().add(massage);
                                massage.setMassageNumber(UserManager.users.get(i).getChats().get(j).getChats().size());

                            }

                        }
                    }


                }

            }while(!text.equals("0") );
            mainPanel(username, destUsername);

        }
        public static void editMassage(String username, String destUsername){
            int l = 0;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (((UserManager.users.get(i).getChats().get(j).getPerson1().equals(username) &&
                                UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                        (destUsername)) ||
                                (UserManager.users.get(i).getChats().get(j).getPerson1().equals
                                        (destUsername) &&
                                        UserManager.users.get(i).getChats().get(j).getPerson2().equals
                                                (username)))){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());

                            }



                        }
                    }
                }
                l = i;
            }
            System.out.println("Enter number of chat you want to edit :");
            int choice = sc.nextInt();
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername)){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                if(choice == UserManager.users.get(i).getChats().get(j).getChats().get(k).getMassageNumber()){
                                    if (username.equals(UserManager.users.get(i).getChats().get(j).getPerson1())){
                                        System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString() + "\n" +
                                                "This is your text.\n" +
                                                "Enter new text please .");
                                        String newText = sc.next();
                                        UserManager.users.get(i).getChats().get(j).getChats().get(k).setTextBody(newText);
                                    }
                                    else{
                                        System.out.println("You cant edit this massage");

                                    }





                                }

                            }



                        }
                    }
                }
                l = i;
            }
            mainPanel(username, destUsername);



        }
        public static void likeDislike(String username, String destUsername){
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)) {
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername)){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());
                            }
                                System.out.println("Enter the number of chat you want to like or dislike");
                                int number = sc.nextInt();
                                for (int l = 0; l < UserManager.users.get(i).getChats().get(j).getChats().size(); l++) {
                                    if (UserManager.users.get(i).getChats().get(j).getChats().get(l).getMassageNumber() == number){
                                        System.out.println("Enter a number\n" +
                                                "1) Like\n" +
                                                "2) Dislike");
                                        int choice = sc.nextInt();
                                        switch (choice){
                                            case 1:
                                                UserManager.users.get(i).getChats().get(j).getChats().get(l).setLike("Like");
                                                break;
                                            case 2:
                                                UserManager.users.get(i).getChats().get(j).getChats().get(l).setLike("Dislike");
                                                break;
                                        }
                                    }

                                }


                        }

                    }
                }

            }
            mainPanel(username, destUsername);



        }
        public static void bothSideDelete(String username, String destUsername){
            int choice = 0 ;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (username.equals(UserManager.users.get(i).getUsername())){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername)){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());
                            }
                            System.out.println("Enter number of the chat you want  to delete");
                            choice = sc.nextInt();
                            choice--;
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getMassageNumber() == choice){
                                    if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getOriginUsername().equals(username)){
                                        UserManager.users.get(i).getChats().get(j).getChats().remove(choice);
                                        System.out.println("chat deleted");
                                    }
                                }

                            }
                        }

                    }
                }

            }

            for (int i = 0; i < UserManager.users.size(); i++) {
                if (destUsername.equals(UserManager.users.get(i).getUsername())){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (UserManager.users.get(i).getChats().get(j).getPerson2().equals(username)){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getMassageNumber() == choice){
                                    if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getOriginUsername().equals(username)){
                                        UserManager.users.get(i).getChats().get(j).getChats().remove(choice);
                                    }
                                }

                            }
                        }

                    }
                }

            }
            mainPanel(username, destUsername);
        }
        public static void oneSideDelete(String username, String destUsername){
            int choice = 0 ;
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (username.equals(UserManager.users.get(i).getUsername())){
                    for (int j = 0; j < UserManager.users.get(i).getChats().size(); j++) {
                        if (UserManager.users.get(i).getChats().get(j).getPerson2().equals(destUsername)){
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                System.out.println(UserManager.users.get(i).getChats().get(j).getChats().get(k).toString());
                            }
                            System.out.println("Enter number of the chat you want  to delete");
                            choice = sc.nextInt();
                            choice--;
                            for (int k = 0; k < UserManager.users.get(i).getChats().get(j).getChats().size(); k++) {
                                if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getMassageNumber() == choice){
                                    if (UserManager.users.get(i).getChats().get(j).getChats().get(k).getOriginUsername().equals(username)){
                                        UserManager.users.get(i).getChats().get(j).getChats().remove(choice);
                                        System.out.println("chat deleted");
                                    }
                                }

                            }
                        }

                    }
                }

            }
            mainPanel(username, destUsername);
        }
        public static void checkSpam(String text, String username, String destUsername) throws NullPointerException {
            if (text.equals(" ")){
                throw new NullPointerException("You chant send just a space");

            }

        }
    }

    public static class StringFormatException extends Exception{

        public StringFormatException(String message) {
            super(message);
        }
    }

    public static class NumberFormatException extends Exception {
        public NumberFormatException(String message) {
            super(message);
        }
    }

    public static class NullPointerException extends Exception{

        public NullPointerException(String message) {
            super(message);
        }
    }

    public static class NotFoundException extends Exception{

        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidInfoException extends Exception{

        public InvalidInfoException(String message) {
            super(message);
        }
    }

    public static class EaesyPasswordException extends Exception {
        public EaesyPasswordException(String message) {
            super(message);
        }
    }

    public static class DuplicatedUserException extends Exception{
        public DuplicatedUserException(String message) {
            super(message);
        }
    }
}
