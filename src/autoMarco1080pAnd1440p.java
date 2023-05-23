import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;


public class autoMarco1080pAnd1440p {
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode = 18;//marco pause
    int tempGunMode = 0;
    boolean on_or_off = false;
    boolean isMute = true;
    String gun = "杜绝收费，从你我做起";
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

    double deadConfidence = 0.50; //confidence level
    double confidence = 0.90; //confidence level

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





    public autoMarco1080pAnd1440p() {

        //scanner config file for gun mode
        JFrame frame = new JFrame("Apex全自动宏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.jpg").getImage());
        frame.setSize(500, 350);
        frame.setResizable(false);
        //frame in the center of screen
        frame.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 1));
        frame.add(panel1);
        //create button
        JButton button1 = new JButton("开/关");
        //change button font size
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton mute = new JButton("开/关提示音");
        mute.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton button3 = new JButton("Switch Language");
        button3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton release = new JButton("释放脚本文件到本地");
        release.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton button4 = new JButton("自动清理内存");
        button4.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton aimCross = new JButton("开/关游戏准星");
        aimCross.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton urlButton = new JButton("GitHub链接");
        String url = "https://github.com/JiaqinKang/apexNoRecoilMarco";
        urlButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));

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
        gun.setFont(new Font("微软雅黑", Font.PLAIN, 30));
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
                mute.setText("unmute/mute");
                button3.setText("切换语言");
                release.setText("Release Script Ok");
                button4.setText("Auto Clean Memory");
                gun.setText(this.gun);
                urlButton.setText("GitHub Link");
                aimCross.setText("Game Aim Cross");
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
            copyFileUsingJava7Files(from, to);
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
        JOptionPane.showMessageDialog(frame, """
                Please make sure your game language is set to Chinese, or the script will not work unless you update the weapon screen shots to english snapshots.
                请确保游戏语言设置为中文，否则脚本将无法工作，除非您更新武器屏幕截图为英文快照
                                
                操作说明:
                游戏中鼠标灵敏度为1.6，鼠标加速度关闭，罗技驱动——>指针设置——>报告率改为1000，加速关闭
                哈沃克涡轮增压器自动检测
                暴走燃烧弹自动检测
                兔子跳 设置下蹲键为 l // 鼠标启动按键需要自己设置，各种鼠标按键图在文件夹里
                Numlock小键盘锁开关宏,支持腰射和开镜压枪
                p2020 全自动开枪需要设置第二开枪键为p键
                
             
             
                                
                English:
                Mouse sensitivity in game is 1.6, mouse acceleration is off, Logitech driver->pointer settings->report rate is 1000, acceleration is off
                Havoc turbocharger is automatically detected
                thermite is automatically detected
                rabbit jump set crouch key to l // mouse start key needs to be set by yourself, various mouse keys binding are in the folder
                Numlock switch for on/off macro, support hip fire and aim down sight
                - p2020 and Wingman pistol need to set the second shooting key to p key; 
                """
        );


        button1.addActionListener(e -> {
            if (on_or_off) {
                on_or_off = false;
                button1.setBackground(Color.RED);
                System.out.println("close");
            } else {
                on_or_off = true;
                button1.setBackground(Color.GREEN);
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
        }


        // Capture weapon area
        x = (int) (SystemWidth *0.781);
        y = (int) (SystemHeight *0.9);
        height = (int) (SystemHeight *0.1);
        width = (int) (SystemWidth *0.2);
        //System.out.println(x + " " + y + " " + width + " " + height);

        if (SystemWidth == 3440 && SystemHeight == 1440) {
            System.out.println("1440p x 3440p");
            x1 = (550);
            y1 = (100);
            width1 = 200;
            height1 = 100;
        }else if (SystemWidth == 2560 && SystemHeight == 1440) {
            System.out.println("1440p x 2560p");
            //capture for looting area
            x1 = (SystemWidth / 16);
            y1 = (SystemHeight / 10);
            width1 = (int) (SystemWidth / 8.5) - x1;
            height1 = (int) ((SystemHeight / 8.5) - y1) * 2;
        } else if (SystemHeight == 1600 && SystemWidth == 2560) {
            System.out.println("1600p x 2560p");
            x1 = (100);
            y1 = (100);
            width1 = (int)(SystemWidth/ 7);
            height1 = (int) (SystemHeight /6);
        } else {
            System.out.println("其他分辨率");
            x1 = (100);
            y1 = (100);
            width1 = (int)(SystemWidth/ 7);
            height1 = (int) (SystemHeight /6);
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

        System.out.println(SystemWidth + " " + SystemHeight);



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

    private void copyFileUsingJava7Files(File source, File dest) throws IOException {
        //if file already exist then delete it first
        if (dest.exists()) {
            dest.delete();//delete file
        }
        //copy file
        Files.copy(source.toPath(), dest.toPath());//copy file
    }

    private void scan() throws AWTException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        while (true) {


            // create robot to capture screen in specific area with parameters from above
            try {
                Robot robot = new Robot();

                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height)); //capture weapon area
                BufferedImage dead = robot.createScreenCapture(new Rectangle(x1, y1, width1, height1)); //capture the death box area

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", byteArrayOutputStream);
                ImageIO.write(dead, "jpg", byteArrayOutputStream1);

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
            if (imageDetection(_2dead,"dead",false) >= deadConfidence) {
                this.gun = "Dead";
                gunMode = 18;
                switchNow();
            } else if (imageDetection(_2dead,"dead1",false) >= deadConfidence) {
                this.gun = "Dead 1";
                gunMode = 18;
                switchNow();
            } else if (imageDetection(_2dead,"dead2",false) >= deadConfidence) {
                this.gun = "Dead 2";
                gunMode = 18;
                switchNow();
            } else if ( imageDetection(_2dead,"blackMarket",false) >= deadConfidence){
                this.gun = "Black Market";
                gunMode = 18;
                switchNow();
            } else if (imageDetection(_1weapon,"r99",false) >= confidence) {
                this.gun = "R-99 SMG";
                gunMode = 1;
                switchNow();
            } else if (imageDetection(_1weapon,"r301",false) >= confidence) {
                this.gun = "R-301 Carbine";
                gunMode = 2; // 14 点射
                switchNow();
            } else if (imageDetection(_1weapon,"alternatorSMG",false) >= confidence ) {
                this.gun = "Alternator SMG";
                gunMode = 3;
                switchNow();
            } else if (imageDetection(_1weapon,"voltSMG",false) >= confidence ) {
                this.gun = "Volt SMG";
                gunMode = 4;
                switchNow();
            } else if (imageDetection(_1weapon,"m600Spitfire",false) >= confidence) {
                this.gun = "M600 Spitfire";
                gunMode = 10;
                switchNow();
            } else if (imageDetection(_1weapon, "vk",false) >= confidence ) {
                this.gun = "VK-47 Flatline";
                gunMode = 6;
                switchNow();
            } else if (imageDetection(_1weapon,"re-45",false) >= confidence ) {
                this.gun = "RE_45 Auto";
                gunMode = 13;
                switchNow();
            } else if (imageDetection(_1weapon,"rampageLMG",false) >= confidence ) {
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
            } else if (imageDetection(_1weapon,"p2020",false) >= confidence ) {
                this.gun = "P2020";
                gunMode = 9;
                switchNow();
            } else if (imageDetection(_1weapon,"havoc",false) >= confidence ) {
                gunMode = 16;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence ) {
                    this.gun = "Havoc + turbocharger";
                    write_to_file1(1);
                }else {
                    this.gun = "Havoc Rifle";
                    write_to_file1(0);
                }
            } else if (imageDetection(_1weapon,"devotionLMG",false) >= confidence ) {
                gunMode = 5;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence ) {
                    this.gun = "Devotion LMG + turbocharger";
                    write_to_file1(1);
                }else {
                    this.gun = "Devotion LMG";
                    write_to_file1(0);
                }
            } else if (imageDetection(_1weapon,"car",false) >= confidence ) {
                this.gun = "C.A.R SMG";
                gunMode = 20;
                switchNow();
            } else if (imageDetection(_1weapon,"car2",false) >= confidence ) {
                this.gun = "C.A.R SMG";
                gunMode = 20;
                switchNow();
            } else if (imageDetection(_1weapon,"g7",false) >= confidence ) {
                this.gun = "G7 Scout";
                gunMode = 17;
                switchNow();
            } else if (imageDetection(_1weapon,"hemLock",false)>= confidence) {
                if (imageDetection(_1weapon,"hemLockSingle",false) >= confidence ) {
                    this.gun = "Hemlock + Single";
                    gunMode = 8;
                    switchNow();
                }else {
                    this.gun = "Hemlock + Burst";
                    gunMode = 18;
                    switchNow();
                }
            } else if (imageDetection(_1weapon,"prowlerBurstPDW",false) >= confidence ) {
                this.gun = "Prowler Burst PDW";
                gunMode = 7;
                switchNow();
            } else if (imageDetection(_1weapon,"L-Star",false) >= confidence ) {
                this.gun = "L_Star EMG";
                gunMode = 11;
                switchNow();
            } else if (imageDetection(_1weapon,"wingMan",false) >= confidence){
                this.gun = "Wingman";
                gunMode = 12;
                switchNow();
            }

            else {
                this.gun = "未检测到支持武器";
                gunMode = 18;
                switchNow();
            }
            if (memoryClean){
                //System.out.println("Memory Clean");
                System.gc();
            }
            if (!on_or_off) { //if the program is closed, break the loop
                this.gun ="关闭成功";
                break; //break the loop
            }
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
            Mat checkItemMat = Imgcodecs.imread("weapon/" + screenResolution + "/" + checkItem +".jpg");
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

    //run the program
    public static void main(String[] args) {
        new autoMarco1080pAnd1440p();
    }


}
