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


public class autoMarco1080pAnd1440pBackup {
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode = 18;//marco pause
    int tempGunMode = 0;
    boolean on_or_off = false;
    boolean isMute = true;
    boolean r99 = false;
    boolean r301 = false;
    boolean alternatorSMG = false;
    boolean voltSMG = false;
    boolean m600Spitfire = false;
    boolean vk = false;
    boolean re_45 = false;
    boolean rampageLMG = false;
    boolean havocRifle = false;
    boolean p2020 = false;
    boolean devotionLMG = false;
    boolean car = false;
    boolean car2 = false;
    boolean g7 = false;
    boolean hemLock = false;
    boolean prowlerBurstPDW = false;
    boolean L_Star = false;
    boolean wingMan = false;
    boolean _30_30Repeater = false;
    boolean bocekCompoundBow = false;
    boolean chargeRifle = false;
    boolean eva8Auto = false;
    boolean kraber50CalSniper = false;
    boolean longbowDMR = false;
    boolean mastiffShotgun = false;
    boolean mozambiqueShotgun = false;
    boolean peaceKeeper = false;
    boolean sentinel = false;
    boolean tripleTake = false;
    boolean bag = false;
    boolean dead = false;
    boolean gameMenu = false;
    boolean lobby = false;
    boolean setting = false;
    boolean exitMenu = false;
    boolean blackMarket = false;
    boolean selectChampion = false;
    boolean inMap = false;
    String gun = "no Weapon";
    File from = new File("ScriptSeason14开镜_腰射.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\ScriptSeason14.lua");

    File control = new File("C:\\Users\\Public\\Downloads\\123.lua");

    //check  system resolution
    int SystemWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int SystemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //language
    String language = "中文";
    String screenResolution;

    double deadConfidence = 0.50; //confidence level
    double confidence = 0.85; //confidence level


    public autoMarco1080pAnd1440pBackup() {

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
                哈沃克和轻型机枪必须有涡轮增压器,Numlock开关宏,开枪时按住鼠标右键启动宏
                - p2020 和 辅助手枪 全自动开枪需要设置第二开枪键为p键
                                
                English:
                Mouse sensitivity in game is 1.6, mouse acceleration is off, Logitech driver->pointer settings->report rate is 1000, acceleration is off
                Havoc and light machine gun must have turbocharger, Numlock switch for on/off macro, hold the right mouse button to start the macro when shooting);
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
            int x = (int) (SystemWidth *0.781);
            //540
            int y = (int) (SystemHeight *0.9);

            int height = (int) (SystemHeight *0.1);

            int width = (int) (SystemWidth *0.2);
            //System.out.println(x + " " + y + " " + width + " " + height);


            //capture for looting area
            int x1 = (SystemWidth / 16);

            int y1 = (SystemHeight / 10);

            int width1 = (int) (SystemWidth / 8.5) - x1;

            int height1 = (int) ((SystemHeight / 8.5) - y1) * 2;
            //System.out.println(x1 + " " + y1 + " " + width1 + " " + height1);

            //capture for setting area

            int x2 = (int) (SystemWidth *0.02);

            int y2 = (int) (SystemHeight * 0.9);

            int height2 = (int) (SystemHeight * 0.1);

            int width2 = (int) (SystemWidth *0.06);
            //System.out.println(x2 + " " + y2 + " " + width2 + " " + height2);


            // create robot to capture screen in specific area with parameters from above
            try {
                Robot robot = new Robot();
                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height)); //capture weapon area
                BufferedImage dead = robot.createScreenCapture(new Rectangle(x1, y1, width1, height1)); //capture the death box area
                BufferedImage setting = robot.createScreenCapture(new Rectangle(x2, y2, width2, height2)); //capture the setting area
                ImageIO.write(image, "jpg", new File("weapon/screenshot.jpg")); //save weapon area screenshot
                ImageIO.write(dead, "jpg", new File("weapon/deadScreenshot.jpg")); //save dead area screenshot
                ImageIO.write(setting, "jpg", new File("weapon/settingScreenshot.jpg")); //save setting area screenshot
            } catch (IOException | AWTException e) {
                //pop up window to tell user to run as admin

                if (language.equals("中文")) {
                    JOptionPane.showMessageDialog(null, "图片截图失败,请以管理员身份运行程序,并在GitHub上提出bug", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Image sceenshot failed, please run as administrator or report bugs on GitHub", "Error", JOptionPane.ERROR_MESSAGE);
                }
                throw new RuntimeException(e);
            }

            Mat _1weapon = Imgcodecs.imread("weapon/screenshot.jpg");
            Mat _2dead = Imgcodecs.imread("weapon/deadScreenshot.jpg");
            Mat _3setting = Imgcodecs.imread("weapon/settingScreenshot.jpg");

//            check if the player is looting a dead chest
            if (imageDetection(_2dead,"dead",false) >= deadConfidence) {
                System.out.println("dead");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead";
            }
            else
                if (imageDetection(_2dead,"dead1",false) >= deadConfidence) {
                System.out.println("dead 1");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead 1";
            }
            else if (imageDetection(_2dead,"dead2",false) >= deadConfidence) {
                System.out.println("Dead 2");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead 2";
            }
            else if ( imageDetection(_2dead,"blackMarket",false) >= confidence && !blackMarket){
                System.out.println("Black Market");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Black Market";
                switchNow();
            }
            else {
                dead = false;
                blackMarket = false;
                lobby = false;
            }

            //check if is in game menu or lobby
//            if (!gameMenu && imageDetection(_1weapon,"menu",false) >= confidence) {
//                restGuns();
//                gameMenu = true;
//                this.gun = "Game menu";
//                gunMode = 18;
//                System.out.println("Game menu");
//            } else
            if (!lobby && imageDetection(_1weapon,"returnLobby",false) >= confidence) {
                restGuns();
                lobby = true;
                this.gun = "Game over";
                gunMode = 18;
                System.out.println("Game over");
                switchNow();
            } else if (imageDetection(_3setting,"setting",false) >= confidence && !setting) {
                restGuns();
                setting = true;
                this.gun = "Setting";
                gunMode = 18;
                System.out.println("Setting");
                switchNow();
            } else if (imageDetection(_1weapon,"exitToMenu",false) >= confidence && !exitMenu){
                restGuns();
                exitMenu = true;
                this.gun = "Exit menu";
                gunMode = 18;
                System.out.println("Exit menu");
                switchNow();
            } else if (imageDetection(_3setting,"inMap",false)>= confidence && !inMap){
                restGuns();
                inMap = true;
                this.gun = "In map";
                gunMode = 18;
                System.out.println("In map");
                switchNow();
            }
//            else if (imageDetection(_1weapon,"selectChampion", false) >= confidence && !selectChampion){
//                restGuns();
//                selectChampion = true;
//                this.gun = "Selecting champion";
//                gunMode = 18;
//                System.out.println("Selecting champion");
//            }

            //if the game is not in the menu or game over screen then scan for weapons
            if (!dead && !blackMarket ) {
                if (imageDetection(_3setting,"bag",false) >= confidence && !bag) {
                    System.out.println("bag");
                    gunMode = 18;
                    restGuns();//reset all guns
                    r99 = true; //set r99 to true to avoid multiple detection
                    this.gun = "inventory";
                    switchNow();
                } else if (!r99 && imageDetection(_1weapon,"r99",false) >= confidence) {
                    System.out.println("r99");
                    gunMode = 1;
                    restGuns();//reset all guns
                    r99 = true; //set r99 to true to avoid multiple detection
                    this.gun = "R-99 SMG";
                    switchNow();
                } else if (!r301 && imageDetection(_1weapon,"r301",false) >= confidence) {
                    System.out.println("r301");
                    gunMode = 2;
                    restGuns();
                    r301 = true;
                    this.gun = "R-301 Carbine";
                    switchNow();
                } else if (imageDetection(_1weapon,"alternatorSMG",false) >= confidence && !alternatorSMG) {
                    System.out.println("alternatorSMG");
                    gunMode = 3;
                    restGuns();
                    alternatorSMG = true;
                    this.gun = "Alternator SMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"voltSMG",false) >= confidence && !voltSMG) {
                    System.out.println("voltSMG");
                    gunMode = 4;
                    restGuns();
                    voltSMG = true;
                    this.gun = "Volt SMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"m600Spitfire",false) >= confidence && !m600Spitfire) {
                    System.out.println("M600 Spitfire");
                    gunMode = 10;
                    restGuns();
                    m600Spitfire = true;
                    this.gun = "M600 Spitfire";
                    switchNow();
                } else if (imageDetection(_1weapon, "vk",false) >= confidence && !vk) {
                    System.out.println("vk");
                    gunMode = 6;
                    restGuns();
                    vk = true;
                    this.gun = "VK-47 Flatline";
                    switchNow();
                } else if (imageDetection(_1weapon,"re-45",false) >= confidence && !re_45) {
                    System.out.println("re_45");
                    gunMode = 13;
                    restGuns();
                    re_45 = true;
                    this.gun = "RE_45 Auto";
                    switchNow();
                } else if (imageDetection(_1weapon,"rampageLMG",false) >= confidence && !rampageLMG) {
                    System.out.println("rampageLMG");
                    gunMode = 19;
                    restGuns();
                    rampageLMG = true;
                    this.gun = "Rampage LMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"p2020",false) >= confidence && !p2020) {
                    System.out.println("p2020");
                    gunMode = 9;
                    restGuns();
                    p2020 = true;
                    this.gun = "P2020";
                    switchNow();
                } else if (imageDetection(_1weapon,"havoc",false) >= confidence && !havocRifle) {
                    System.out.println("havoc");
                    gunMode = 16;
                    restGuns();
                    havocRifle = true;
                    this.gun = "Havoc Rifle";
                    switchNow();
                } else if (imageDetection(_1weapon,"devotionLMG",false) >= confidence && !devotionLMG) {
                    System.out.println("devotionLMG");
                    gunMode = 5;
                    restGuns();
                    devotionLMG = true;
                    this.gun = "Devotion LMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"car",false) >= confidence && !car) {
                    System.out.println("car");
                    gunMode = 20;
                    restGuns();
                    //same recoil now both set to true for debug
                    car = true;
                    car2 = true;
                    this.gun = "C.A.R SMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"car2",false) >= confidence && !car2) {
                    System.out.println("car2");
                    gunMode = 20;
                    restGuns();
                    //same recoil now both set to true for debug
                    car = true;
                    car2 = true;
                    this.gun = "C.A.R SMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"g7",false) >= confidence && !g7) {
                    System.out.println("g7");
                    gunMode = 17;
                    restGuns();
                    g7 = true;
                    this.gun = "G7 Scout";
                    switchNow();
                } else if (imageDetection(_1weapon,"hemLock",false)>= confidence && !hemLock) {
                    System.out.println("hemloke");
                    gunMode = 8;
                    restGuns();
                    hemLock = true;
                    this.gun = "Hemlok Burst";
                    switchNow();
                } else if (imageDetection(_1weapon,"prowlerBurstPDW",false) >= confidence && !prowlerBurstPDW) {
                    System.out.println("prowlerBurstPDW");
                    gunMode = 7;
                    restGuns();
                    prowlerBurstPDW = true;
                    this.gun = "Prowler Burst PDW";
                    switchNow();
                } else if (imageDetection(_1weapon,"L-Star",false) >= confidence && !L_Star) {
                    System.out.println("L_Star");
                    gunMode = 11;
                    restGuns();
                    L_Star = true;
                    this.gun = "L_Star EMG";
                    switchNow();
                } else if (imageDetection(_1weapon,"wingMan",false) >= confidence && !wingMan) {
                    System.out.println("wingMan");
                    gunMode = 18 ; //same as p2020
                    restGuns();
                    wingMan= true;
                    this.gun = "Wingman";
                    switchNow();
                }
                ////////////////////////////////////////////////////////////////////////////
                /////////////////////////////guns not supported/////////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                else if (imageDetection(_1weapon,"30_30Repeater",false) >= confidence && ! _30_30Repeater){
                    System.out.println("30_30Repeater");
                    gunMode = 18;//pause recoil
                    restGuns();
                    _30_30Repeater = true;
                    this.gun = "30-30 Repeater";
                    switchNow();
                }else if (imageDetection(_1weapon,"bocekCompoundBow",false) >= confidence && !bocekCompoundBow){
                    System.out.println("bocekCompoundBow");
                    gunMode = 18;//
                    restGuns();
                    bocekCompoundBow = true;
                    this.gun = "Bocek Compound Bow";
                    switchNow();
                }else if (imageDetection(_1weapon,"chargeRifle",false) >= confidence && !chargeRifle){
                    System.out.println("chargeRifle");
                    gunMode = 18;//
                    restGuns();
                    chargeRifle = true;
                    this.gun = "Charge Rifle";
                    switchNow();
                }else if (imageDetection(_1weapon,"eva8Auto",false) >= confidence && !eva8Auto){
                    System.out.println("eva8Auto");
                    gunMode = 18;//
                    restGuns();
                    eva8Auto = true;
                    this.gun = "Eva-8 Auto";
                    switchNow();
                }else if (imageDetection(_1weapon,"kraber50CalSniper",false) >= confidence && !kraber50CalSniper){
                    System.out.println("kraber50CalSniper");
                    gunMode = 18;//
                    restGuns();
                    kraber50CalSniper = true;
                    this.gun = "Kraber 50-Cal Sniper";
                    switchNow();
                }else if (imageDetection(_1weapon,"longbowDMR",false) >= confidence && !longbowDMR){
                    System.out.println("longbowDMR");
                    gunMode = 18;//
                    restGuns();
                    longbowDMR = true;
                    this.gun = "Longbow DMR";
                    switchNow();
                }else if (imageDetection(_1weapon,"mastiffShotGun",false) >= confidence && !mastiffShotgun){
                    System.out.println("mastiff");
                    gunMode = 18;//
                    restGuns();
                    mastiffShotgun = true;
                    this.gun = "Mastiff";
                    switchNow();
                }else if (imageDetection(_1weapon,"mozambiqueShotGun",false) >= confidence && !mozambiqueShotgun){
                    System.out.println("mozambique");
                    gunMode = 18;//
                    restGuns();
                    mozambiqueShotgun = true;
                    this.gun = "Mozambique";
                    switchNow();
                }else if (imageDetection(_1weapon,"peaceKeeper",false) >= confidence && !peaceKeeper){
                    System.out.println("peaceKeeper");
                    gunMode = 18;//
                    restGuns();
                    peaceKeeper = true;
                    this.gun = "PeaceKeeper";
                    switchNow();
                } else if (imageDetection(_1weapon,"sentinel",false) >= confidence && !sentinel){
                    System.out.println("sentinel");
                    gunMode = 18;//
                    restGuns();
                    sentinel = true;
                    this.gun = "Sentinel";
                    switchNow();
                } else if ( imageDetection(_1weapon,"tripleTake",false) >=confidence && !tripleTake ){
                    System.out.println("tripleTake");
                    gunMode = 18;//
                    restGuns();
                    tripleTake = true;
                    this.gun = "TripleTake";
                    switchNow();
                }

            }
            System.gc();//garbage collection

            if (!on_or_off) { //if the program is closed, break the loop
                restGuns();
                break; //break the loop
            }
        }
    }

    //rest all weapons to false
    public void restGuns() {
        //guns
        r99 = false;
        r301 = false;
        alternatorSMG = false;
        voltSMG = false;
        m600Spitfire = false;
        vk = false;
        re_45 = false;
        rampageLMG = false;
        p2020 = false;
        havocRifle = false;
        devotionLMG = false;
        car = false;
        car2 = false;
        g7 = false;
        hemLock = false;
        prowlerBurstPDW = false;
        wingMan= false;
        L_Star = false;
        _30_30Repeater = false;
        bocekCompoundBow = false;
        chargeRifle = false;
        eva8Auto = false;
        kraber50CalSniper = false;
        longbowDMR = false;
        mastiffShotgun = false;
        mozambiqueShotgun = false;
        peaceKeeper = false;
        sentinel = false;
        tripleTake = false;


        //settings
        dead = false;
        gameMenu = false;
        lobby = false;
        bag = false;
        setting = false;
        blackMarket = false;
        inMap = false;
        selectChampion = false;

    }

    public void switchNow(){
        //write to file only when the gun is changed
        if (tempGunMode != gunMode) {
            tempGunMode = gunMode; //update the gunMode
            write_to_file(tempGunMode); //write to file
            playBeep(); //play beep sound
            System.out.println("write to file");
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
        new autoMarco1080pAnd1440pBackup();
    }


}
