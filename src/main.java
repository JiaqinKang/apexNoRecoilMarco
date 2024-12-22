import org.json.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class main {
    String version = "6.6.6";
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode = 18;//marco pause
    int tempGunMode = 0;
    boolean on_or_off = false;
    boolean isMute = true;
    String gun = "杜绝收费，从你我做起,GitHub点点星星，谢谢";
    File from = new File("Script.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\Script.lua");

    File control = new File("C:\\Users\\Public\\Downloads\\main.lua");

    File thermiteControl = new File("C:\\Users\\Public\\Downloads\\thermite.lua");

    File havocControl = new File("C:\\Users\\Public\\Downloads\\turbo_state.lua");

    //check  system resolution
    int SystemWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int SystemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //language
    String language = "中文";
    String screenResolution;

    double deadConfidence = 0.45; //confidence level
    double confidence = 0.77; //confidence level

    int threshold = 200;

    int x;
    int y;
    int width;
    int height;

    int x1;
    int y1;
    int width1;
    int height1;

    boolean memoryClean = false;

    boolean aimCrossCheck = false;
    aim aimDialog = null;

    Mat _1weapon = null;

    Mat _2dead = null;

    String GITHUB_API_URL = "https://api.github.com/repos/JiaqinKang/apexNoRecoilMarco/releases/latest";

    String latestVersion;

    boolean[] booleanArray = new boolean[23];

    boolean lgs =false;
    boolean ghub =false;

    String[] gunNames = {
            "<html><center>R301 single<br>301卡宾枪单点<br></center></html>",
            "R301 卡宾枪",
            "VK 平行步枪",

            "<html><center>VK single<br>平行步枪单点</center></html>",
            "<html><center>Alternator SMG<br>转换者</center></html>",
            "Car",

            "P2020",
            "<html><center>Devotion LMG<br>专注</center></html>",
            "Havoc 哈沃克",

            "<html><center>G7<br>(不安装双发班机)</center></html>",
            "<html><center>RE-45<br>自动手枪</center></html>",
            "<html><center>L-STAR<br>能量机枪</center></html>",

            "<html><center>Wing Man<br>辅助手枪</center></html>",
            "<html><center>m600<br>喷火轻机枪</center></html>",
            "<html><center>Rampage<br>LMG暴走</center></html>",

            "<html><center>Prowler Burst <br>PDW Burst PD<br>猎兽冲锋枪连发</center></html>",
            "<html><center>Volt SMG<br>电能冲锋枪</center></html>",
            "R99 冲锋枪",

            "<html><center>HemLock Single<br>赫姆洛克 单点</center></html>",
            "<html><center>RevengGoddess<br>复仇女神</center></html>",
            "<html><center>Prowler Burst <br> PDW_auto<br>猎兽全自动</center></html>",

            "<html><center>HemLock<br>赫姆洛克 连发</center></html>",
    };




    public main() {
        GitHubReleaseChecker ();
        checkForUpdates(latestVersion);


        //scanner config file for gun mode
        JFrame frame = new JFrame("Apex全自动宏 "+version);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.jpg").getImage());
        frame.setSize(1000, 600);
        frame.setResizable(false);
        //frame in the center of screen
        frame.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 1));


        JPanel wrapper = new JPanel(new GridLayout(1, 2)); // Creating a wrapper panel with 1x2 grid layout
        wrapper.add(panel1); // This is just a placeholder. Replace this with any other component you want to add in the second column.

        frame.add(wrapper);
        frame.setVisible(true);
        //create button and change button font size
        JButton button1 = new JButton("开/关");
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton mute = new JButton("开/关提示音");
        mute.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton button3 = new JButton("Switch Language");
        button3.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton release = new JButton("释放脚本文件到本地");
        release.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton button4 = new JButton("自动清理内存");
        button4.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton aimCross = new JButton("开/关游戏准星");
        aimCross.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton urlButton = new JButton("GitHub链接");
        String url = "https://github.com/JiaqinKang/apexNoRecoilMarco";
        urlButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));



        //////////////////////////////////////////////////////////////////
        JPanel gunPanel = new JPanel();
        gunPanel.setLayout(new GridLayout(0, 3)); // Adjust the grid layout columns as needed

        booleanArray[0] = true; // r301_single
        booleanArray[1] = true; // r301
        booleanArray[2] = true; // vk

        booleanArray[3] = true; // vk_single
        booleanArray[4] = true; // alternator
        booleanArray[5] = true; // car

        booleanArray[6] = true; // p2020
        booleanArray[7] = true; // devotion
        booleanArray[8] = true; // havoc

        booleanArray[9] = true; // g7
        booleanArray[10] = true; // re45
        booleanArray[11] = true; // lstar

        booleanArray[12] = true; // wingman
        booleanArray[13] = true; // m600
        booleanArray[14] = true; // rampage

        booleanArray[15] = true; // prowlerBurstPDW_burst
        booleanArray[16] = true; // volt
        booleanArray[17] = true; // r99

        booleanArray[18] = true; // hemlock_single
        booleanArray[19] = true; // revengeGoddess
        booleanArray[20] = true; // prowlerBurstPDW_auto

        booleanArray[21] = true; // hemlock——burst


//        create button for each gun
        JButton[] gunButtons = new JButton[gunNames.length];

//        use for each to create gunbuttons
        for (int i = 0; i < gunNames.length; i++) {
            int temp = i;
            gunButtons[i] = new JButton(gunNames[i]);
            gunButtons[i].setFont(new Font("微软雅黑", Font.PLAIN, 14));
            gunButtons[i].setOpaque(true);
            gunButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
            gunButtons[i].setBorder(BorderFactory.createLineBorder(Color.black));
            gunButtons[i].setBackground(Color.GREEN);

            final int index = i; // Need a final variable for lambda expression
            // disable pwd auto
            if (i==20){
                gunButtons[i].setBackground(Color.gray);
                gunButtons[i].setEnabled(false);
            }
            else{
                gunButtons[i].addActionListener(e -> {
                    if (!booleanArray[temp]){
                        booleanArray[temp] = true;
                        gunButtons[temp].setBackground(Color.GREEN);
                    } else {
                        booleanArray[temp] = false;
                        gunButtons[index].setBackground(Color.red);
                    }
                });

            }
            // Add the button to the panel
            gunPanel.add(gunButtons[i]);
        }

//        System.out.println(gunButtons[1]);



        wrapper.add(gunPanel); // Add the gunPanel to the wrapper panel

        /////////////////////////////////////////////////////////////////
        JPanel deadConfidencePanel = new JPanel(new GridLayout(1,3));

        JLabel deadvalueLabel = new JLabel("箱/黑市检测(%)"); // Label to display the current value
        deadvalueLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        deadvalueLabel.setHorizontalAlignment(JLabel.CENTER);
        // Create a JTextField for manual input
        JTextField deadtextField = new JTextField(String.valueOf((int) (deadConfidence*100)));
        deadtextField.setHorizontalAlignment(JTextField.CENTER);
        deadtextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Ignore non-numeric characters
                }
            }
        });

        // Add a FocusListener to handle the input limit
        deadtextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                double input = Double.parseDouble(deadtextField.getText());
                if (input > 100.0) {
                    deadtextField.setText("100"); // Set to 100 if input is greater than 100
                }
                if (input <= 0.0) {
                    deadtextField.setText("0"); // Set to 100 if input is greater than 100
                }
            }
        });

        // deadConfidence slider
        JSlider deadslider = new JSlider(JSlider.HORIZONTAL, 0, 10000, (int) (deadConfidence*10000)); // Ranging from 0 to 10000 for 0% to 100%
        deadslider.setMajorTickSpacing(25);

        deadslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double sliderValue = deadslider.getValue();
                double deadConfidenceValue = sliderValue / 100.0; // Convert to a percentage value between 0 and 100
                if (deadConfidenceValue > 100.0) {
                    deadConfidenceValue = 100.0; // Cap it at 100
                }

                sliderValue = (sliderValue/10000.0);

                String formattedValue = String.format("%.2f", sliderValue); // Format the value with two decimal places
//                System.out.println("formattedValue = " + formattedValue);

                deadConfidence = sliderValue;

                deadtextField.setText(String.valueOf(sliderValue*100)); // Update the text field with the formatted value
            }
        });

        // Create the submitButton
        JButton submitButton = new JButton("确定");

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the value from the text field and limit it to 100 if necessary
                    double input = Double.parseDouble(deadtextField.getText());
                    if (input > 100.0) {
                        input = 100.0;
                    }

                    // Update the slider and text field based on the input
                    int sliderValue = (int) (input * 100);
                    deadslider.setValue(sliderValue);
                    deadtextField.setText(String.valueOf(input));

                    // Update deadConfidence value
                    deadConfidence = input / 100.0;
                } catch (NumberFormatException ex) {
                    // Handle invalid input (e.g., non-numeric text)
                    // You can show an error message or handle it as appropriate for your application
                }
            }
        });

        deadConfidencePanel.add(deadvalueLabel);
        deadConfidencePanel.add(deadtextField);
        deadConfidencePanel.add(submitButton);

        JPanel weaponConfidencePanel = new JPanel(new GridLayout(1,3));

        JLabel weaponvalueLabel = new JLabel("武器检测(%)"); // Label to display the current value
        weaponvalueLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        weaponvalueLabel.setHorizontalAlignment(JLabel.CENTER);

// Create a JTextField for manual input
        JTextField weapontextField = new JTextField(String.valueOf((int) (confidence*100)));
        weapontextField.setHorizontalAlignment(JTextField.CENTER);
        weapontextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Ignore non-numeric characters
                }
            }
        });

// Add a FocusListener to handle the input limit
        weapontextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                double input = Double.parseDouble(weapontextField.getText());
                if (input > 100.0) {
                    weapontextField.setText("100"); // Set to 100 if input is greater than 100
                }
                if (input <= 0.0) {
                    weapontextField.setText("0"); // Set to 100 if input is greater than 100
                }
            }
        });

// weaponConfidence slider
        JSlider weaponslider = new JSlider(JSlider.HORIZONTAL, 0, 10000, (int) (confidence*10000)); // Ranging from 0 to 10000 for 0% to 100%
        weaponslider.setMajorTickSpacing(25);

        weaponslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double sliderValue = weaponslider.getValue();
                double weaponConfidenceValue = sliderValue / 100.0; // Convert to a percentage value between 0 and 100
                if (weaponConfidenceValue > 100.0) {
                    weaponConfidenceValue = 100.0; // Cap it at 100
                }

                sliderValue = (sliderValue/10000.0);

                String formattedValue = String.format("%.2f", sliderValue); // Format the value with two decimal places

                confidence = sliderValue;

                weapontextField.setText(String.valueOf(sliderValue*100)); // Update the text field with the formatted value
            }
        });

// Create the submit button
        JButton weaponSubmitButton = new JButton("确定");

// Add ActionListener to the submit button
        weaponSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the value from the text field and limit it to 100 if necessary
                    double input = Double.parseDouble(weapontextField.getText());
                    if (input > 100.0) {
                        input = 100.0;
                    }

                    // Update the slider and text field based on the input
                    int sliderValue = (int) (input * 100);
                    weaponslider.setValue(sliderValue);
                    weapontextField.setText(String.valueOf(input));

                    // Update weaponConfidence value
                    confidence = input / 100.0;
                } catch (NumberFormatException ex) {
                    // Handle invalid input (e.g., non-numeric text)
                    // You can show an error message or handle it as appropriate for your application
                }
            }
        });

        weaponConfidencePanel.add(weaponvalueLabel);
        weaponConfidencePanel.add(weapontextField);
        weaponConfidencePanel.add(weaponSubmitButton);



        //when the program close, delete the script file
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                to.delete();
                control.delete();
                thermiteControl.delete();
                havocControl.delete();
            }
        });


        //current gun
        JLabel gun = new JLabel(this.gun);
        gun.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        gun.setHorizontalAlignment(JLabel.CENTER);
        //change gun colour
        gun.setForeground(Color.blue);
        //always on top
        frame.setAlwaysOnTop(true);

        panel1.add(gun);
        panel1.add(button1);
        panel1.add(mute);
        panel1.add(button4);
        panel1.add(urlButton);
        panel1.add(aimCross);
        panel1.add(button3);
        panel1.add(release);
        panel1.add(deadConfidencePanel);
        panel1.add(deadslider);
        panel1.add(weaponConfidencePanel);
        panel1.add(weaponslider);


        // add invisible button to change gun mode
        JButton gunMode = new JButton("");
        gunMode.setVisible(false);

        frame.setVisible(true); //show frame

        //auto trigger gun mode button every 0.1s
        Timer timer = new Timer(100, e -> gunMode.doClick());

        button1.setBackground(Color.white);
        mute.setBackground(Color.red);
        release.setBackground(Color.white);

        //add button listener to update lable
        gunMode.addActionListener(e -> {
            //update gun label
            if (language.equals("中文")) {
                gun.setText(this.gun);
            } else {
                gun.setText(this.gun);
            }
            gun.validate();//update label
        });

        //if mute button is pressed then mute the sound
        mute.addActionListener(e -> {
            // Toggle the mute state
            isMute = !isMute;
            // Update the button text and color based on the mute state
            if (isMute) {
                mute.setBackground(Color.RED);
            } else {
                mute.setBackground(Color.GREEN);
            }
        });

        button3.addActionListener(e -> {
            if (language.equals("中文")) {
                language = "English";
                button1.setText("On/Off");
                mute.setText("Unmute/Mute");
                button3.setText("切换语言");
                release.setText("Release Script Ok");
                button4.setText("Auto Clean Memory");
                gun.setText(this.gun);
                urlButton.setText("GitHub Link");
                aimCross.setText("Game Aim Cross");
                deadvalueLabel.setText("Chest/Dead(%)");
                weaponvalueLabel.setText("Weapon(%)");
                submitButton.setText("Confirm");
                weaponSubmitButton.setText("Confirm");

            } else {
                language = "中文";
                button1.setText("开/关");
                mute.setText("开/关提示音");
                button3.setText("Switch Language");
                release.setText("脚本文件OK");
                button4.setText("自动清理内存");
                gun.setText(this.gun);
                urlButton.setText("GitHub链接");
                aimCross.setText("开/关游戏准星");
                deadvalueLabel.setText("箱/黑市检测(%)");
                weaponvalueLabel.setText("武器检测(%)");
                submitButton.setText("确定");
                weaponSubmitButton.setText("确定");
            }
        });

        urlButton.addActionListener(e -> {
            try {
                // Open the URL in the default browser
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
                // Handle any exceptions that occur while opening the URL
            }
        });



        button4.addActionListener(e -> {
            // set memoryClean to true
            memoryClean = true;
            // disable button
            button4.setEnabled(false);
            // set background to red
            button4.setBackground(Color.red);
            //show message 卡顿请关闭
            if (language.equals("中文")) {
                JOptionPane.showMessageDialog(frame, "内存清理中,如果游戏卡顿请请勿开启内存自动清理，重启程序即可");
            } else {
                JOptionPane.showMessageDialog(frame, "Memory cleaning, if the game is stuck, please turn off the memory automatic cleaning");
            }
        });
        //create aimCross click
        aimCross.addActionListener(e -> {
            if (!aimCrossCheck) {
                aimDialog = new aim();
                aimCrossCheck = true;
                aimCross.setBackground(Color.green);
                //play a beep sound
                Toolkit.getDefaultToolkit().beep();
                //show message
                if (language.equals("中文")) {
                    JOptionPane.showMessageDialog(frame, "准星已开启,确保游戏是无边框窗口模式");
                } else {
                    JOptionPane.showMessageDialog(frame, "Aim Cross is on, make sure the game is in borderless window mode");
                }
            } else {
                //remove aim dialog
                aimCrossCheck = false;
                aimDialog.dispose();
                aimCross.setBackground(Color.white);
                Toolkit.getDefaultToolkit().beep();
            }
        });


        //release script file to local
        try {
            copyFile(from, to);
            write_to_file(18); // 123.lua
            write_to_file2(0); // thermite.lua
            write_to_file1(0); // turbo_state.lua
            //disable button
            release.setEnabled(false);
            //show message
            //JOptionPane.showMessageDialog(frame, "脚本文件已释放到本地,Script File Released to Local path");
            release.setText("脚本文件OK");
            release.setBackground(Color.green);
        } catch (IOException e1) {
            e1.printStackTrace();
            //show message
            JOptionPane.showMessageDialog(frame, "脚本文件释放失败,Script File Release Failed");
            release.setBackground(Color.red);
            //disable all button
            button1.setEnabled(false);
            mute.setEnabled(false);
            release.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }


        //pop up message when start the program
        String message = """
                请确保游戏语言设置为中文，否则黑市、死亡将无法暂停压枪.
                       
                操作说明:
                游戏中鼠标灵敏度为1.6，鼠标加速度关闭，罗技驱动——>指针设置——>报告率改为1000，加速关闭
                可以调整罗技灵驱动里的鼠标移动灵敏度来解决游戏里1.6太慢的问题，这样不会影响到压枪
                关闭游戏内的鼠标速度！！！！！！！！！！！
                Numlock小键盘锁开关宏,支持腰射和开镜压枪
                连发单点枪只支持lgs，选择ghub会自动无视不支持的枪,如果你不想要连发的枪也可以选择ghub一键关闭所有连发单点枪
                
                Please make sure that the game language is set to Chinese, otherwise the black market and death will not be able to pause recoil control.
                              
                Instructions:
                In the game, set the mouse sensitivity to 1.6 with mouse acceleration turned off.
                In the Logitech driver:
                    1. go to Pointer Settings, 
                    2. change the report rate to 1000, and turn off acceleration.
                You can adjust the mouse movement sensitivity in the Logitech driver to address the issue of being too slow at 1.6 in the game, without affecting recoil control.
                Turn off the in-game mouse speed !!!!!!!!!
                Use the Numlock keypad lock switch macro to support hip fire and aiming down sights recoil control.
                For burst firing single-shot guns, only Logitech Gaming Software (lgs) is supported. 
                Selecting Logitech G HUB (ghub) will automatically ignore guns that are not supported. 
                If you don't want to use burst firing guns, you can choose ghub to easily turn off all burst firing single-shot guns.
                """;
        Object[] options = {"LGS", "GHub"};

        int n = JOptionPane.showOptionDialog(
                frame,
                message,
                "Game Settings",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]  // default option
        );

        if (n == JOptionPane.YES_OPTION) {
            System.out.println("LGS selected");
            lgs = true;
            ghub = false;
        } else if (n == JOptionPane.NO_OPTION) {
            System.out.println("GHub selected");
            ghub = true;
            lgs = false;

            for (int i = 0 ; i < booleanArray.length; i++){
                if (i%3 == 0){
                    booleanArray[i] = false;
                    gunButtons[i].setBackground(Color.red);
                    gunButtons[i].setEnabled(false);
                }
            }



        }


        button1.addActionListener(e -> {
            if (on_or_off) {
                on_or_off = false;
                button1.setBackground(Color.RED);
                for (int i = 0; i < gunButtons.length; i++) {
//                    if ghub is true then skip 0,3,6,9,12,15,18,21
                if (ghub){
                        if (i%3 == 0){
                            continue;
                        }
                    }
                    gunButtons[i].setEnabled(true);
                }
                System.out.println("close");
            } else {
                on_or_off = true;
                button1.setBackground(Color.GREEN);
//              disable all gun buttons
                for (JButton gunButton : gunButtons) {
                    gunButton.setEnabled(false);
                }
                System.out.println("open");
            }
        });

        frame.setVisible(true); //show frame


        timer.start();

        //System.out.println(SystemWidth + " " + SystemHeight);

        if (SystemHeight == 1080) {
            //scan 1080p
            screenResolution = "1080";
        } else if (SystemHeight == 1440) {
            //scan 1440p
            screenResolution = "1440";
        } else if (SystemHeight == 1600){
            //scan 1600p
            screenResolution = "1600";
        }else if (SystemHeight == 2160){
            //scan 2160 4k
            screenResolution = "2160";
        }


        // Capture weapon area
        x = (int) (SystemWidth *0.781);
        y = (int) (SystemHeight *0.85);
        height = (int) (SystemHeight-y);
        width = (int) (SystemWidth -x);
//        System.out.println(x + " " + y + " " + width + " " + height);

        // Black market or dead detection
        if (SystemWidth == 3440 && SystemHeight == 1440) {
            System.out.println("1440p x 3440p");
            x1 = (550);
            y1 = (100);
            width1 = 200*3;
            height1 = 100*3;
        }else if (SystemWidth == 2560 && SystemHeight == 1440) {
            System.out.println("1440p x 2560p");
            //capture for looting area
            x1 = (SystemWidth / 16);
            y1 = (SystemHeight / 10);
            width1 = (int) (SystemWidth / 8.5 *3) - x1;
            height1 = (int) ((SystemHeight / 8.5 *3) - y1) * 2;
        } else if (SystemHeight == 1600 && SystemWidth == 2560) {
            System.out.println("1600p x 2560p");
            x1 = (100);
            y1 = (100);
            width1 = (int)(SystemWidth/ 5 *3);
            height1 = (int) (SystemHeight /6*3);
        } else {
            System.out.println("其他分辨率/1080p");
            x1 = (100);
            y1 = (100);
            width1 = (int)(SystemWidth/ 5*3);
            height1 = (int) (SystemHeight /6*3);
        }

        //auto trigger to ensure the num lock is on for macro to work
        boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
        if (isOn) {
            System.out.println("Numlock is on");
        } else {
            System.out.println("Numlock is off");
            //turn on numlock
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.setLockingKeyState(KeyEvent.VK_NUM_LOCK, Boolean.TRUE);

            System.out.println("Numlock is now turn on");
        }

//        System.out.println(SystemWidth + " " + SystemHeight);

//      future function with scrolled detection, disabled for now
//        // Create a consumer to handle the scroll direction detection
//        Consumer<Boolean> scrollConsumer = isScrollingUp -> {
//            if (isScrollingUp) {
//                System.out.println("Scrolled Up");
//            } else {
//                System.out.println("Scrolled Down");
//            }
//        };
//        // Attach the mouse scroll listener to the panel
//        addMouseScrollListener(panel1, scrollConsumer);


        while (true) {

            if (on_or_off) {
                try {
                    scan();
                } catch (AWTException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }



    private void write_to_file(int i) {
        //write lua file to C:\Users\Public\Downloads
        String path = "C:\\Users\\Public\\Downloads\\";
        String file_name = "main.lua";
        String file_path = path + file_name;
        String file_content = "qx1_1 = GunCombination1_1[" + i + "]";
        File file = new File(file_path);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(file_content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write_to_file2(int i) {
        //write lua file to C:\Users\Public\Downloads
        String path = "C:\\Users\\Public\\Downloads\\";
        String file_name = "thermite.lua";
        String file_path = path + file_name;
        String file_content = "BZ_LRJ = " + i;
        File file = new File(file_path);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(file_content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write_to_file1(int i ){
        //write lua file to C:\Users\Public\Downloads
        String path = "C:\\Users\\Public\\Downloads\\";
        String file_name = "turbo_state.lua";
        String file_path = path + file_name;
        String file_content = "turbo_state = " + i;
        File file = new File(file_path);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(file_content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(File source, File destination) throws IOException {
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    private void scan() throws AWTException {
        boolean r301_single = booleanArray[0];
        boolean r301 = booleanArray[1];
        boolean vk = booleanArray[2];

        boolean vk_single = booleanArray[3];
        boolean alternator = booleanArray[4];
        boolean car = booleanArray[5];

        boolean p2020 = booleanArray[6];
        boolean devotion = booleanArray[7];
        boolean havoc = booleanArray[8];

        boolean g7 = booleanArray[9];
        boolean re45 = booleanArray[10];
        boolean lstar = booleanArray[11];

        boolean wingman = booleanArray[12];
        boolean m600 = booleanArray[13];
        boolean rampage = booleanArray[14];

        boolean prowler = booleanArray[15];
        boolean volt = booleanArray[16];
        boolean r99 = booleanArray[17];

        boolean hemlock_single = booleanArray[18];
        boolean revengeGoddess = booleanArray[19];
        boolean prowlerBurstPDW_auto = booleanArray[20];

        boolean hemlock = booleanArray[21];

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        while (true) {


            // create robot to capture screen in specific area with parameters from above
            try {
                Robot robot = new Robot();

                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height)); //capture weapon area
                BufferedImage dead = robot.createScreenCapture(new Rectangle(x1, y1, width1, height1)); //capture the death box area

                // Process the image to keep only the parts you want
                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        int pixel = image.getRGB(x, y);
                        int grayValue = (pixel >> 16) & 0xff; // Extract the red component as the grayscale value
                        int newPixel = (grayValue > threshold) ? pixel : 0; // Set black for unwanted parts
                        image.setRGB(x, y, newPixel);
                    }
                }
                // Process the image to keep only the parts you want
                for (int y = 0; y < dead.getHeight(); y++) {
                    for (int x = 0; x < dead.getWidth(); x++) {
                        int pixel = dead.getRGB(x, y);
                        int grayValue = (pixel >> 16) & 0xff; // Extract the red component as the grayscale value
                        int newPixel = (grayValue > threshold) ? pixel : 0; // Set black for unwanted parts
                        dead.setRGB(x, y, newPixel);
                    }
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();

                ImageIO.write(image, "png", byteArrayOutputStream);
                ImageIO.write(dead, "png", byteArrayOutputStream1);

//                debug checking
//                ImageIO.write(image, "png", new File("weapon_area.png"));
//                ImageIO.write(dead, "png", new File("dead_area.png"));

                //to byte array
                byte[] weaponArea = byteArrayOutputStream.toByteArray();
                byte[] deadArea = byteArrayOutputStream1.toByteArray();

                // convert to opencv mat
                MatOfByte matOfByte = new MatOfByte(weaponArea);
                MatOfByte matOfByte1 = new MatOfByte(deadArea);
                _1weapon = Imgcodecs.imdecode(matOfByte,Imgcodecs.IMREAD_UNCHANGED);
                _2dead = Imgcodecs.imdecode(matOfByte1,Imgcodecs.IMREAD_UNCHANGED);

            } catch (IOException | AWTException e) {
                //pop up window to tell user to run as admin
                JOptionPane.showMessageDialog(null, "图片截图失败,请以管理员身份运行程序,并在GitHub上提出bug", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }


//            check if the player is looting a dead chest



            if ( imageDetection(_2dead,"blackMarket_english",false) >= deadConfidence){
                this.gun = "Pause fire";
                gunMode = 18;
                switchNow();
            } else if (imageDetection(_2dead,"dead_english",false) >= deadConfidence) {
                this.gun = "Pause fire";
                gunMode = 18;
                switchNow();
            }else if (imageDetection(_2dead,"dead",false) >= deadConfidence) {
                this.gun = "Pause fire";
                gunMode = 18;
                switchNow();
            } else if ( imageDetection(_2dead,"blackMarket",false) >= deadConfidence){
                this.gun = "Pause fire";
                gunMode = 18;
                switchNow();
            } else if (imageDetection(_1weapon,"r99",false) >= confidence && r99) {
                this.gun = "R-99 SMG";
                gunMode = 1;
                switchNow();
            } else if (imageDetection(_1weapon,"r301",false) >= confidence) {
//                hemlock single same picture as r301
                if (imageDetection(_1weapon,"hemLockSingle",false) >= confidence && r301_single) {
                    this.gun = "R-301 Carbine Single";
                    gunMode = 14;
                    switchNow();
                }else if (r301 && imageDetection(_1weapon,"hemLockSingle",false) < confidence){
                    this.gun = "R-301 Carbine auto";
                    gunMode = 2;
                    switchNow();
                }else{
                    this.gun = "R-301 Carbine off";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"alternatorSMG",false) >= confidence && alternator) {
                this.gun = "Alternator SMG";
                gunMode = 3;
                switchNow();
            } else if (imageDetection(_1weapon,"voltSMG",false) >= confidence && volt ) {
                this.gun = "Volt SMG";
                gunMode = 4;
                switchNow();
            } else if (imageDetection(_1weapon,"m600Spitfire",false) >= confidence && m600) {
                this.gun = "M600 Spitfire";
                gunMode = 10;
                switchNow();
            } else if (imageDetection(_1weapon, "vk",false) >= confidence && vk) {
                if (imageDetection(_1weapon,"hemLockSingle",false) >= confidence && vk_single) {
                    this.gun = "VK-47 Flatline Single";
                    gunMode = 15;
                    switchNow();
                }else if(vk && imageDetection(_1weapon,"hemLockSingle",false) < confidence){
                    this.gun = "VK-47 Flatline auto";
                    gunMode = 6;
                    switchNow();
                }else{
                    this.gun = "VK-47 Flatline off";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"re-45",false) >= confidence && re45) {
                this.gun = "RE_45 Auto";
                gunMode = 13;
                switchNow();
            } else if (imageDetection(_1weapon,"rampageLMG",false) >= confidence && rampage) {
                gunMode = 19;
                switchNow();
                if (imageDetection(_1weapon,"thermite",false) >= confidence ) {
                    this.gun = "Rampage LMG + Thermite";
                    write_to_file2(1);
                    System.out.println("Thermite");
                }else {
                    this.gun = "Rampage LMG";
                    write_to_file2(0);
                }
            } else if (imageDetection(_1weapon,"p2020",false) >= confidence && p2020) {
                this.gun = "P2020";
                gunMode = 9;
                switchNow();
            } else if (imageDetection(_1weapon,"havoc",false) >= confidence && havoc) {
                gunMode = 16;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence ) {
                    this.gun = "Havoc + turbocharger";
                    write_to_file1(1);
                }else {
                    this.gun = "Havoc Rifle";
                    write_to_file1(0);
                }
            } else if (imageDetection(_1weapon,"devotionLMG",false) >= confidence && devotion ) {
                gunMode = 5;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence && devotion) {
                    this.gun = "Devotion LMG + turbocharger";
                    write_to_file1(1);
                }else {
                    this.gun = "Devotion LMG";
                    write_to_file1(0);
                }
            } else if (imageDetection(_1weapon,"car",false) >= confidence && car) {
                this.gun = "C.A.R SMG";
                gunMode = 20;
                switchNow();
            } else if (imageDetection(_1weapon,"g7",false) >= confidence && g7) {
                if (imageDetection(_1weapon,"hemLockSingle",false) >= confidence ) {
                    this.gun = "G7 Scout single";
                    gunMode = 17;
                    switchNow();
                }else {
                    this.gun = "G7 Scout";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"hemLock",false)>= confidence) {
                if (imageDetection(_1weapon,"hemLockSingle",false) >= confidence && hemlock_single) {
                    this.gun = "Hemlock + Single";
                    gunMode = 8;
                    switchNow();
                }else if (imageDetection(_1weapon,"hemLockSingle",false) < confidence && hemlock){
                    this.gun = "Hemlock + Burst";
                    gunMode = 22;
                    switchNow();
                }else{
                    this.gun = "Hemlock off";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"prowlerBurstPDW",false) >= confidence) {
                if (imageDetection(_1weapon,"prowlerBurstPDW_burst",false) >= confidence && prowler) {
                    this.gun = "Prowler Burst PDW Burst";
                    gunMode = 7;
                    switchNow();
                }else if (prowlerBurstPDW_auto && imageDetection(_1weapon,"prowlerBurstPDW_burst",false) < confidence) {
                    this.gun = "Prowler Burst PDW Auto";
                    gunMode = 23;
                    switchNow();
                }else{
                    this.gun = "Prowler Burst PDW off";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"L-Star",false) >= confidence && lstar) {
                this.gun = "L_Star EMG";
                gunMode = 11;
                switchNow();
            } else if (imageDetection(_1weapon,"wingMan",false) >= confidence&& wingman){
                this.gun = "Wingman";
                gunMode = 12;
                switchNow();
            }  else if (imageDetection(_1weapon,"revengGoddess",false) >= confidence && revengeGoddess){
                this.gun = "Reveng Goddess";
                gunMode = 21;
                switchNow();
            }

            else {
                this.gun = "未检测到支持武器,No supported gun Detected";
                gunMode = 18;
                switchNow();
            }
            if (memoryClean){
                //System.out.println("Memory Clean");
                System.gc();
            }
            if (!on_or_off) { //if the program is closed, break the loop
                this.gun ="关闭成功，Turned off";
                gunMode = 18;
                switchNow();
                break; //break the loop
            }
//            System.out.println(deadConfidence);
        }
    }

    public void switchNow(){
        //write to file only when the gun is changed
        if (tempGunMode != gunMode) {
            tempGunMode = gunMode; //update the gunMode
            write_to_file(tempGunMode); //write to file
            playBeep(); //play beep sound
            System.out.println(gun);
        }
    }

    public double imageDetection(Mat _1weapon_2dead_3setting, String checkItem, boolean debugVerbose) {
        try {
            Mat outputImage = new Mat();
            Mat checkItemMat = Imgcodecs.imread("weapon/" + screenResolution + "/" + checkItem +".png");
            Imgproc.matchTemplate(_1weapon_2dead_3setting, checkItemMat, outputImage, machMethod);//
            Core.MinMaxLocResult confidenceValue = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
            if (debugVerbose) {
                System.out.println("checkItem: " + checkItem);
                System.out.println("screenshot: " +_1weapon_2dead_3setting);
                System.out.println("screenResolution: " + screenResolution);
                System.out.println("confidenceValue.maxVal = " + confidenceValue.maxVal);
                // out
            }
            return confidenceValue.maxVal;
        } catch (Exception e) {
            // output error message to ui
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return 0;
        }
    }

    public void playBeep() {
        if (!isMute) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public static class aim extends JDialog {

        public aim() {
            // 设置窗口属性
            setUndecorated(true);
            setPreferredSize(new Dimension(40, 40));
            setAlwaysOnTop(true);
            setBackground(new Color(0, 0, 0, 0));

            // 显示窗口
            pack();
            setLocation(getTopLeftPoint());
            setVisible(true);
        }

        // 获取窗口显示位置（屏幕中心）
        private Point getTopLeftPoint() {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();

            int screenWidth = gd.getDisplayMode().getWidth();
            int screenHeight = gd.getDisplayMode().getHeight();

            int windowX = (int) (screenWidth / 2 - 20);
            int windowY = (int) (screenHeight / 2 - 20);

            return new Point(windowX, windowY);
        }

        public void paint(Graphics g) {
            super.paint(g);

            // 设置画笔颜色为红色
            g.setColor(Color.RED);

            // 绘制圆形
            int size = Math.min(getWidth(), getHeight()) - 10;
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;
            g.drawOval(x, y, size, size);

        }
    }

//    public static void addMouseScrollListener(Component component, Consumer<Boolean> onScrollDirectionDetected) {
//        MouseWheelListener listener = new MouseWheelListener() {
//            @Override
//            public void mouseWheelMoved(MouseWheelEvent e) {
//                // If e.getWheelRotation() < 0, it means the mouse wheel was rotated up.
//                onScrollDirectionDetected.accept(e.getWheelRotation() < 0);
//            }
//        };
//
//        component.addMouseWheelListener(listener);
//    }



    public void GitHubReleaseChecker (){
        try {
            URL url = new URL(GITHUB_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            if (connection.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(null, "Cannot connect to GitHub. 无法连接GitHub，请检查网络或者使用魔法.",
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            connection.disconnect();

            // 解析JSON以获取最新版本的标签名
            JSONObject jsonObject = new JSONObject(response.toString());
            latestVersion = jsonObject.getString("tag_name");
            System.out.println("Latest Version: " + latestVersion);

        } catch (IOException e) {
            // Handle the connection issue gracefully
            System.err.println("Error connecting to GitHub: " + e.getMessage());
            latestVersion = "Unknown"; // Set a default value indicating unknown version
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkForUpdates(String latestVersion) {
        if (isNewerVersion(version, latestVersion)) {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "A new version is available. Would you like to update?" +
                            "有新版本要更新吗?",
                    "Update available", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) { // yes exit the program and open GitHub
                openWebpage("https://github.com/JiaqinKang/apexNoRecoilMarco/releases");
                System.exit(1);
            }
//            continue use the old version
        }
    }

    private boolean isNewerVersion(String currentVersion, String newVersion) {

        if (latestVersion.equals("Unknown")) {
            System.err.println("Error connecting to GitHub");
            System.out.println("Cannot compare versions: Unknown version");
            JOptionPane.showMessageDialog(null, "Cannot connect to GitHub. 无法连接GitHub，请检查网络或者使用魔法.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] currentNumbers = currentVersion.split("\\.");
        String[] newNumbers = newVersion.split("\\.");

        for (int i = 0; i < Math.min(currentNumbers.length, newNumbers.length); i++) {
            int current = Integer.parseInt(currentNumbers[i]);
            int newNum = Integer.parseInt(newNumbers[i]);
            if (newNum > current) {
                return true;
            } else if (newNum < current) {
                return false;
            }
        }
        return newNumbers.length > currentNumbers.length;
    }

    private void openWebpage(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //run the program
    public static void main(String[] args) {
        new main();
    }


}