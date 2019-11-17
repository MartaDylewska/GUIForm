
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class FormJPanel extends JPanel {

    private JLabel formTitle,loginTitle,passTitle,sexTitle,progKnowledgeTitle,selfDescTitle;
    private JTextField loginField,passField;
    private JButton generatePass,resetMe,sendMe ;
    private JRadioButton sexWoman,sexMan;
    private ButtonGroup sexButtonGroup;
    private JCheckBox javaChBx,cChBx,rubyChBx,vbnetChBx;
    private JTextArea selfDescArea;
    private ArrayList<String> validationList = new ArrayList<>();
    private ArrayList<Component> editableComponents = new ArrayList<>();
    private ArrayList<JLabel> starsList = new ArrayList<>();

    //for validation check and star draw
    private boolean loginFieldCheck = false,passFieldCheck = false,sexCheck = false,progrKnowChBxCheck = false,selfDescAreaCheck = false;

    private JLabel starLogin,starPass,starSex,starProgKnow,starSelfDesc;

    public Dimension getPreferredSize() {
        return new Dimension(500, 600);
    }

    public FormJPanel() {
        setLayout(null);

        createFormTitle();
        add(formTitle);

        createLoginTitle();
        add(loginTitle);
        createLoginField();
        add(loginField);
        createStarLogin();
        add(starLogin);

        createPassTitle();
        add(passTitle);
        createPassField();
        add(passField);
        createStarPass();
        add(starPass);
        createGeneratePassButton();
        add(generatePass);

        createSexTitle();
        add(sexTitle);
        createSexRadioChoice();
        add(sexWoman);
        add(sexMan);
        createStarSex();
        add(starSex);

        createProgKnowledgeTitle();
        add(progKnowledgeTitle);
        createProgKnowledgeChckBx();
        add(javaChBx);
        add(cChBx);
        add(rubyChBx);
        add(vbnetChBx);
        createStarProgKnow();
        add(starProgKnow);

        createSelfDescTitle();
        add(selfDescTitle);
        createSelfDescArea();
        add(selfDescArea);
        createStarSelfDesc();
        add(starSelfDesc);

        setAllStarsInvisible();

        createResetBtn();
        add(resetMe);

        createSendBtn();
        add(sendMe);

    }



    public void createGeneratePassButton(){
        generatePass = new JButton();
        generatePass.setBounds(460,160,30,30);
        try {
            generatePass.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Settings-icon.jpg").getFile()));
           }
        catch(Exception e){
            System.out.println("Problem z obrazkiem na przycisku \"Generuj Hasło\"");
            System.exit(-1);
        }

        generatePass.addActionListener(e ->
                passField.setText(PasswordOperations.generateRandomPass()));
    }

    public void createSendBtn() {
        sendMe = new JButton();
        sendMe.setText("Wyślij");
        sendMe.setFont(new Font("Serif", Font.PLAIN, 20));
        sendMe.setBounds(300, 520, 150, 40);

        sendMe.addActionListener(e -> {

            setAllFieldsCheckFalse();
            setAllStarsInvisible();
            performValidation();
            disableAll();
            if (validationList.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Pełen sukces");
                System.exit(0);

            } else {
                ErrorJFrame oknoBledow = new ErrorJFrame(550, 300, "Błedy formularza");
                oknoBledow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                oknoBledow.setVisible(true);
                for (String s : validationList)
                    oknoBledow.getErrorTextArea().append(s + "\n");

                oknoBledow.getErrorTextArea().setEnabled(false);
                oknoBledow.getBtnOK().addActionListener(e1 -> {
                    setStarsVisible();
                    enableAll();
                    validationList.clear();
                    oknoBledow.dispose();
                });
            }

        });
    }

    public void createResetBtn() {
        resetMe = new JButton();
        resetMe.setText("Wyczyść dane");
        resetMe.setFont(new Font("Serif", Font.PLAIN, 20));
        resetMe.setBounds(50, 520, 150, 40);
        resetMe.addActionListener(e ->
                performReset());
    }

    public void createSelfDescArea() {
        selfDescArea = new JTextArea();
        selfDescArea.setFont(new Font("Serif", Font.PLAIN, 16));
        selfDescArea.setBounds(50, 380, 400, 110);
        selfDescArea.setBorder(createNewBlackBorder());
        selfDescArea.setWrapStyleWord(true);
        selfDescArea.setLineWrap(true);
        selfDescArea.setDocument(new JTextAreaLimit(170));
        editableComponents.add(selfDescArea);

    }

    public void createSelfDescTitle() {
        String textInsideSelfDescLbl = "Napisz kilka słów o sobie (max 170 znaków)";
        selfDescTitle = new JLabel(textInsideSelfDescLbl, SwingConstants.CENTER);
        selfDescTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        selfDescTitle.setBounds(50, 330, 400, 50);
    }

    public void createProgKnowledgeChckBx() {
        javaChBx = new JCheckBox();
        javaChBx.setText("Java");
        javaChBx.setSelected(true);
        javaChBx.setFont(new Font("Serif", Font.PLAIN, 20));
        javaChBx.setBounds(50, 290, 70, 50);

        cChBx = new JCheckBox();
        cChBx.setText("C++");
        cChBx.setSelected(false);
        cChBx.setFont(new Font("Serif", Font.PLAIN, 20));
        cChBx.setBounds(150, 290, 70, 50);

        rubyChBx = new JCheckBox();
        rubyChBx.setText("Ruby");
        rubyChBx.setSelected(false);
        rubyChBx.setFont(new Font("Serif", Font.PLAIN, 20));
        rubyChBx.setBounds(250, 290, 70, 50);

        vbnetChBx = new JCheckBox();
        vbnetChBx.setText("VB.net");
        vbnetChBx.setSelected(false);
        vbnetChBx.setFont(new Font("Serif", Font.PLAIN, 20));
        vbnetChBx.setBounds(350, 290, 80, 50);

        editableComponents.add(javaChBx);
        editableComponents.add(cChBx);
        editableComponents.add(rubyChBx);
        editableComponents.add(vbnetChBx);
    }

    public void createProgKnowledgeTitle() {
        String textInsideProgKnowLlb = "Znajomość języków programowania";
        progKnowledgeTitle = new JLabel(textInsideProgKnowLlb, SwingConstants.CENTER);
        progKnowledgeTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        progKnowledgeTitle.setBounds(50, 250, 400, 50);
    }

    public void createSexRadioChoice() {
        sexWoman = new JRadioButton();
        sexWoman.setText("Kobieta");
        sexWoman.setFont(new Font("Serif", Font.PLAIN, 20));
        sexWoman.setBounds(200, 210, 100, 30);

        sexMan = new JRadioButton();
        sexMan.setText("Mężczyzna");
        sexMan.setFont(new Font("Serif", Font.PLAIN, 20));
        sexMan.setBounds(300, 210, 130, 30);

        sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(sexWoman);
        sexButtonGroup.add(sexMan);

        editableComponents.add(sexWoman);
        editableComponents.add(sexMan);
    }

    public void createSexTitle() {
        sexTitle = new JLabel();
        sexTitle.setText("Płeć");
        sexTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        sexTitle.setBounds(50, 200, 125, 50);
    }

    public void createPassField() {
        passField = new JTextField();
        passField.setToolTipText("<html>" + "-min 8 znaków, max 16 znaków" + "<br>" + "-min 1 znak specjalny" + "<br>" + "-min 1 cyfra" + "<br>" + "-min 1 duża litera" + "<br>" + "-min 1 mała litera");
        passField.setFont(new Font("Serif", Font.PLAIN, 20));
        passField.setBounds(200, 160, 250, 30);
        passField.setBorder(createNewBlackBorder());
        editableComponents.add(passField);
    }

    public void createLoginField() {
        loginField = new JTextField();
        loginField.setToolTipText("Wpisz tutaj swój login");
        loginField.setFont(new Font("Serif", Font.PLAIN, 20));
        loginField.setBounds(200, 110, 250, 30);
        loginField.setBorder(createNewBlackBorder());
        editableComponents.add(loginField);
    }

    public void createPassTitle() {
        passTitle = new JLabel();
        passTitle.setText("Hasło");
        passTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        passTitle.setBounds(50, 150, 125, 50);
    }

    public void createLoginTitle() {
        loginTitle = new JLabel();
        loginTitle.setText("Login");
        loginTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        loginTitle.setBounds(50, 100, 125, 50);
    }

    public void createFormTitle() {
        String textInsideFormTitle = "FORMULARZ ZGŁOSZENIOWY";
        formTitle = new JLabel(textInsideFormTitle, SwingConstants.CENTER);
        formTitle.setFont(new Font("Serif", Font.PLAIN, 24));
        formTitle.setBounds(50, 25, 400, 50);
        //make font bold
        Font f = formTitle.getFont();
        formTitle.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        //setOpaque = true -> dzięki temu można pomalować każdy pixel wewnątrz granic elementu
        formTitle.setOpaque(true);
        formTitle.setBackground(Color.WHITE);
        formTitle.setBorder(createNewBlackBorder());
    }

    public static Border createNewBlackBorder() {
        return BorderFactory.createLineBorder(Color.BLACK);
    }


    public void performValidation() {
        validLoginField();
        validPassField();
        validSex();
        validProgKnowledge();
        validDescArea();
    }

    public void validLoginField() {

        if (loginField.getText().equals("")) {
            loginFieldCheck = true;
            validationList.add(new String("Proszę wpisać poprawny login"));
        }
    }


    public void validPassField() {

        if (passField.getText().equals("")||!PasswordOperations.isStringOk(passField.getText())){
            passFieldCheck = true;
            validationList.add(new String("Proszę wpisać poprawne hasło"));
        }
    }

    public void validSex() {

        if (!sexWoman.isSelected() && !sexMan.isSelected()) {
            sexCheck = true;
            validationList.add(new String("Proszę wybrać płeć"));
        }
    }

    public void validProgKnowledge() {

        if (!javaChBx.isSelected() && !cChBx.isSelected() && !rubyChBx.isSelected() && !vbnetChBx.isSelected()) {
            progrKnowChBxCheck = true;
            validationList.add(new String("Proszę wybrać przynajmniej jeden język programowania"));
        }

    }

    public void validDescArea() {

        if (selfDescArea.getText().equals("")) {
            selfDescAreaCheck = true;
            validationList.add(new String("Proszę wpisać kilka słów o sobie"));
        }
    }

    public void disableAll() {
        for(Component c: editableComponents){
            c.setEnabled(false);
        }
    }

    public void enableAll(){
        for (Component c: editableComponents){
            c.setEnabled(true);
        }
    }

    public void createStarSelfDesc(){
        starSelfDesc = new JLabel();
        starSelfDesc.setText("*");
        starSelfDesc.setForeground(Color.RED);
        starSelfDesc.setBounds(453,380,10,30);
        starsList.add(starSelfDesc);
    }

    public void createStarProgKnow(){
        starProgKnow = new JLabel();
        starProgKnow.setText("*");
        starProgKnow.setForeground(Color.RED);
        starProgKnow.setBounds(453,290,10,30);
        starsList.add(starProgKnow);
    }

    public void createStarSex(){
        starSex = new JLabel();
        starSex.setText("*");
        starSex.setForeground(Color.RED);
        starSex.setBounds(453,210,10,30);
        starsList.add(starSex);
    }

    public void createStarPass(){
        starPass = new JLabel();
        starPass.setText("*");
        starPass.setForeground(Color.RED);
        starPass.setBounds(453,160,10,30);
        starsList.add(starPass);
    }

    public void createStarLogin(){
        starLogin = new JLabel();
        starLogin.setText("*");
        starLogin.setForeground(Color.RED);
        starLogin.setBounds(453,110,10,30);
        starsList.add(starLogin);
    }

    public void setAllStarsInvisible(){
        for(JLabel star:starsList){
            star.setVisible(false);
        }
    }

    public void setAllFieldsCheckFalse(){
        loginFieldCheck = false;
        passFieldCheck = false;
        sexCheck = false;
        progrKnowChBxCheck = false;
        selfDescAreaCheck = false;
    }

    public void setStarsVisible(){

        if(loginFieldCheck)
            starLogin.setVisible(true);

        if(passFieldCheck)
            starPass.setVisible(true);

        if(sexCheck)
            starSex.setVisible(true);

        if(progrKnowChBxCheck)
            starProgKnow.setVisible(true);

        if(selfDescAreaCheck)
            starSelfDesc.setVisible(true);
    }

    public void performReset() {
        loginField.setText("");
        passField.setText("");
        sexButtonGroup.clearSelection();
        javaChBx.setSelected(false);
        cChBx.setSelected(false);
        rubyChBx.setSelected(false);
        vbnetChBx.setSelected(false);
        selfDescArea.setText("");

    }


}
