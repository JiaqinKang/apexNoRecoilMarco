import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


public class autoMarco1080pAnd1440p {
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode = 18;//marco pause
    int tempGunMode = 0;
    boolean on_or_off = false;
    boolean isMute = true;
    String gun = "no Weapon";
    File from = new File("ScriptSeason14开镜_腰射.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\ScriptSeason14.lua");

    File control = new File("C:\\Users\\Public\\Downloads\\123.lua");

    File thermiteControl = new File("C:\\Users\\Public\\Downloads\\thermite.lua");

    File havocControl = new File("C:\\Users\\Public\\Downloads\\hwk_state.lua");

    //check  system resolution
    int SystemWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int SystemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //language
    String language = "中文";
    String screenResolution;

    double deadConfidence = 0.50; //confidence level
    double confidence = 0.80; //confidence level

    int x;
    int y;
    int width;
    int height;

    int x1;
    int y1;
    int width1;
    int height1;




    public autoMarco1080pAnd1440p() {

        //scanner config file for gun mode
        JFrame frame = new JFrame("Apex腰射全自动宏2k/1080");
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
        JButton button1 = new JButton("开启");
        //change button font size
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton button2 = new JButton("关闭");
        button2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton mute = new JButton("静音");
        mute.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton unMute = new JButton("开启提示音");
        unMute.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton button3 = new JButton("Switch Language");
        button3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        JButton release = new JButton("释放脚本文件到本地");
        release.setFont(new Font("微软雅黑", Font.PLAIN, 30));

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
        panel1.add(button2);
        panel1.add(mute);
        panel1.add(unMute);
        panel1.add(release);
        panel1.add(button3);

        // add invisible button to change gun mode
        JButton gunMode = new JButton("");
        gunMode.setVisible(false);

        frame.setVisible(true); //show frame

        //auto trigger gun mode button every 0.1s
        Timer timer = new Timer(100, e -> gunMode.doClick());

        button1.setBackground(Color.white);
        button2.setBackground(Color.red);
        mute.setBackground(Color.red);
        unMute.setBackground(Color.white);
        release.setBackground(Color.white);

        //add button listener to update lable
        gunMode.addActionListener(e -> {
            //update gun label
            if (language.equals("中文")) {
                gun.setText("当前模式：" + this.gun);
            } else {
                gun.setText("Current Mode：" + this.gun);
            }
            gun.validate();//update label
        });

        //if mute button is pressed then mute the sound
        mute.addActionListener(e -> {
            isMute = true;
            //change button color
            mute.setBackground(Color.GREEN);
            unMute.setBackground(Color.WHITE);
        });
        unMute.addActionListener(e -> {
            isMute = false;
            //change button color
            unMute.setBackground(Color.GREEN);
            mute.setBackground(Color.WHITE);
        });

        button3.addActionListener(e -> {
            if (language.equals("中文")) {
                language = "English";
                button1.setText("Start");
                button2.setText("Stop");
                mute.setText("Mute Sound");
                unMute.setText("Activation Sound");
                button3.setText("切换语言");
                release.setText("Release Script Ok");
                gun.setText("Current Mode: " + this.gun);
            } else {
                language = "中文";
                button1.setText("开启");
                button2.setText("关闭");
                mute.setText("静音");
                unMute.setText("开启提示音");
                button3.setText("Switch Language");
                release.setText("脚本文件OK");
                gun.setText("当前模式：" + this.gun);
            }
        });

        //release script file to local
        try {
            copyFileUsingJava7Files(from, to);
            write_to_file(18);
            write_to_file2(0);
            //disable button
            release.setEnabled(false);
            //show message
            JOptionPane.showMessageDialog(frame, "脚本文件已释放到本地,Script File Released to Local path");
            release.setText("脚本文件OK");
            release.setBackground(Color.green);
        } catch (IOException e1) {
            e1.printStackTrace();
            //show message
            JOptionPane.showMessageDialog(frame, "脚本文件释放失败,Script File Release Failed");
            release.setBackground(Color.red);
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
            on_or_off = true;
            button1.setBackground(Color.GREEN);
            button2.setBackground(Color.WHITE);
            System.out.println("open");
        });
        button2.addActionListener(e -> {
            on_or_off = false;
            button2.setBackground(Color.RED);
            button1.setBackground(Color.WHITE);
            System.out.println("close");
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
        String file_name = "123.lua";
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
        String file_name = "thermit.lua";
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
        String file_name = "hwk_state.lua";
        String file_path = path + file_name;
        String file_content = "hwk_state = " + i;
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

            // Capture weapon area
            x = (int) (SystemWidth *0.781);
            y = (int) (SystemHeight *0.9);
            height = (int) (SystemHeight *0.1);
            width = (int) (SystemWidth *0.2);
            //System.out.println(x + " " + y + " " + width + " " + height);

            if (SystemWidth != 3440) {
                //capture for looting area
                x1 = (SystemWidth / 16);
                y1 = (SystemHeight / 10);
                width1 = (int) (SystemWidth / 8.5) - x1;
                height1 = (int) ((SystemHeight / 8.5) - y1) * 2;
                //System.out.println(x1 + " " + y1 + " " + width1 + " " + height1);
            }else {
                x1 = (550);
                y1 = (100);
                width1 = 200;
                height1 = 100;
            }

            // create robot to capture screen in specific area with parameters from above
            try {
                Robot robot = new Robot();

                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height)); //capture weapon area
                BufferedImage dead = robot.createScreenCapture(new Rectangle(x1, y1, width1, height1)); //capture the death box area

                ImageIO.write(image, "jpg", new File("weapon/screenshot.jpg")); //save weapon area screenshot
                ImageIO.write(dead, "jpg", new File("weapon/deadScreenshot.jpg")); //save dead area screenshot
            } catch (IOException | AWTException e) {
                //pop up window to tell user to run as admin
                JOptionPane.showMessageDialog(null, "图片截图失败,请以管理员身份运行程序,并在GitHub上提出bug", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }

            Mat _1weapon = Imgcodecs.imread("weapon/screenshot.jpg");
            Mat _2dead = Imgcodecs.imread("weapon/deadScreenshot.jpg");

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
                gunMode = 2;
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
                this.gun = "Rampage LMG";
                gunMode = 19;
                switchNow();
                if (imageDetection(_1weapon,"thermite",false) >= confidence ) {
                    this.gun = "Rampage LMG + Thermite";
                    write_to_file2(1);
                    System.out.println("Thermite");
                }else {
                    write_to_file2(0);
                }
            } else if (imageDetection(_1weapon,"p2020",false) >= confidence ) {
                this.gun = "P2020";
                gunMode = 9;
                switchNow();
            } else if (imageDetection(_1weapon,"havoc",false) >= confidence ) {
                this.gun = "Havoc Rifle";
                gunMode = 16;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence ) {
                    this.gun = "Havoc + turbocharger";
                    write_to_file1(1);
                    }else {
                    write_to_file1(0);
                }
            } else if (imageDetection(_1weapon,"devotionLMG",false) >= confidence ) {
                this.gun = "Devotion LMG";
                gunMode = 5;
                switchNow();
                if (imageDetection(_1weapon,"turbo",false) >= confidence ) {
                    this.gun = "Devotion LMG + turbocharger";
                    write_to_file1(1);
                }else {
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
                this.gun = "Hemlok Burst";
                gunMode = 8;
                switchNow();
            } else if (imageDetection(_1weapon,"prowlerBurstPDW",false) >= confidence ) {
                this.gun = "Prowler Burst PDW";
                gunMode = 7;
                switchNow();
            } else if (imageDetection(_1weapon,"L-Star",false) >= confidence ) {
                this.gun = "L_Star EMG";
                gunMode = 11;
                switchNow();
            } else {
                this.gun = "未检测到支持武器";
                gunMode = 18;
                switchNow();
            }

            System.gc(); //free memory
            if (!on_or_off) { //if the program is closed, break the loop
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

        Mat outputImage = new Mat();
        Mat checkItemMat = Imgcodecs.imread("weapon/" + screenResolution + "/" + checkItem +".jpg");
        Imgproc.matchTemplate(_1weapon_2dead_3setting, checkItemMat, outputImage, machMethod);//
        Core.MinMaxLocResult confidenceValue = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        if (debugVerbose) {
            System.out.println("checkItem: " + checkItem);
            System.out.println("screenshot: " +_1weapon_2dead_3setting);
            System.out.println("screenResolution: " + screenResolution);
            System.out.println("confidenceValue.maxVal = " + confidenceValue.maxVal);
        }
        System.gc();
        return confidenceValue.maxVal;
    }

    public void playBeep() {
        if (!isMute) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    //run the program
    public static void main(String[] args) {
        new autoMarco1080pAnd1440p();
    }


}
