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
    boolean r99 = false;
    boolean r301 = false;
    boolean 转换者 = false;
    boolean 电能冲锋枪 = false;
    boolean 喷火 = false;
    boolean vk = false;
    boolean re_45 = false;
    boolean 暴走 = false;
    boolean 哈沃克 = false;
    boolean p2020 = false;
    boolean 专注 = false;
    boolean car = false;
    boolean car2 = false;
    boolean G7 = false;
    boolean 赫姆洛克 = false;
    boolean 猎兽 = false;
    boolean L_Star = false;
    boolean fuZhuShouQiang = false;
    boolean bag = false;

    String gun = "no Weapon";
    File from = new File("ScriptSeason14腰射.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\ScriptSeason14.lua");

    File control = new File("C:\\Users\\Public\\Downloads\\123.lua");
    boolean looting = false;
    boolean dead = false;

    boolean gameMenu = false;

    boolean lobby = false;
    boolean setting =false;
    boolean exitMenu = false ;
    boolean blackMarket = false;



    //check  system resolution
    int SystemWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int SystemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //language
    String language = "中文";
    private String screenResolution;


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
        Timer timer = new Timer(100, e -> {
            gunMode.doClick();
        });

        button1.setBackground(Color.white);
        button2.setBackground(Color.red);
        mute.setBackground(Color.red);
        unMute.setBackground(Color.white);
        release.setBackground(Color.white);

        //add button listener to update lable
        gunMode.addActionListener(e -> {
            //update gun label
            if (language == "中文") {
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
                gun.setText("Current Gun Mode: " + this.gun);
            } else {
                language = "中文";
                button1.setText("开启");
                button2.setText("关闭");
                mute.setText("静音");
                unMute.setText("开启提示音");
                button3.setText("Switch Language");
                release.setText("脚本文件OK");
                gun.setText("当前枪模式：" + this.gun);
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
                throw new RuntimeException(e);
            }

            double deadConfidence = 0.95; //confidence level
            double confidence = 0.90; //confidence level

            //check if the player is looting a dead chest
            if (dead() >= deadConfidence) {
                System.out.println("dead");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead";
            } else if (dead1() >= deadConfidence) {
                System.out.println("dead 1");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead 1";
            } else if (dead2() >= deadConfidence) {
                System.out.println("Dead 2");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Dead 2";
            } else if ( BlackMarket() >= confidence && !blackMarket){
                System.out.println("Black Market");
                restGuns();
                dead = true;
                gunMode = 18;
                this.gun = "Black Market";
            }
            else {
                dead = false;
                blackMarket = false;
            }

            //check if is in game menu or lobby
            if (!gameMenu && lobby() >= confidence) {
                restGuns();
                gameMenu = true;
                this.gun = "Game menu";
                gunMode = 18;
                System.out.println("Game menu");
            } else if (!lobby && returnLobby() >= confidence) {
                restGuns();
                lobby = true;
                this.gun = "Game over";
                gunMode = 18;
                System.out.println("Game over");
            } else if (settings() >= confidence && !setting) {
                restGuns();
                setting = true;
                this.gun = "Setting";
                gunMode = 18;
                System.out.println("Setting");
            } else if (exitMenu() >= confidence && !exitMenu){
                restGuns();
                exitMenu = true;
                this.gun = "Exit menu";
                gunMode = 18;
                System.out.println("Exit menu");

            }

            //if the game is not in the menu or game over screen then scan for weapons
            if (!dead && !blackMarket ) {
                if (bag() >= confidence && !bag) {
                    System.out.println("bag");
                    gunMode = 18;
                    restGuns();//reset all guns
                    r99 = true; //set r99 to true to avoid multiple detection
                    this.gun = "inventory";
                } else if (!r99 && r99() >= confidence) {
                    System.out.println("r99");
                    gunMode = 1;
                    restGuns();//reset all guns
                    r99 = true; //set r99 to true to avoid multiple detection
                    this.gun = "R-99 SMG";
                } else if (!r301 && r301() >= confidence) {
                    System.out.println("r301");
                    gunMode = 2;
                    restGuns();
                    r301 = true;
                    this.gun = "R-301 Carbine";
                } else if (转换者() >= confidence && !转换者) {
                    System.out.println("zhuanHuanZhe");
                    gunMode = 3;
                    restGuns();
                    转换者 = true;
                    this.gun = "Alternator SMG";
                } else if (电能冲锋枪() >= confidence && !电能冲锋枪) {
                    System.out.println("dianNeng");
                    gunMode = 4;
                    restGuns();
                    电能冲锋枪 = true;
                    this.gun = "Volt SMG";
                } else if (喷火() >= confidence && !喷火) {
                    System.out.println("penhuo");
                    gunMode = 10;
                    restGuns();
                    喷火 = true;
                    this.gun = "M600 Spitfire";
                } else if (vk() >= confidence && !vk) {
                    System.out.println("vk");
                    gunMode = 6;
                    restGuns();
                    vk = true;
                    this.gun = "VK-47 Flatline";
                } else if (re_45() >= confidence && !re_45) {
                    System.out.println("re_45");
                    gunMode = 13;
                    restGuns();
                    re_45 = true;
                    this.gun = "RE_45 Auto";
                } else if (暴走() >= confidence && !暴走) {
                    System.out.println("baozou");
                    gunMode = 19;
                    restGuns();
                    暴走 = true;
                    this.gun = "Rampage LMG";
                } else if (p2020() >= confidence && !p2020) {
                    System.out.println("p2020");
                    gunMode = 9;
                    restGuns();
                    p2020 = true;
                    this.gun = "P2020";
                } else if (哈沃克() >= confidence && !哈沃克) {
                    System.out.println("hawoke");
                    gunMode = 16;
                    restGuns();
                    哈沃克 = true;
                    this.gun = "Havoc Rifle";
                } else if (专注() >= confidence && !专注) {
                    System.out.println("zhuanzhu");
                    gunMode = 5;
                    restGuns();
                    专注 = true;
                    this.gun = "Devotion LMG";
                } else if (car() >= confidence && !car) {
                    System.out.println("car");
                    gunMode = 20;
                    restGuns();
                    //same recoil now both set to true for debug
                    car = true;
                    car2 = true;
                    this.gun = "C.A.R SMG";
                } else if (car2() >= confidence && !car2) {
                    System.out.println("car2");
                    gunMode = 20;
                    restGuns();
                    //same recoil now both set to true for debug
                    car = true;
                    car2 = true;
                    this.gun = "C.A.R SMG";
                } else if (G7() >= confidence && !G7) {
                    System.out.println("G7");
                    gunMode = 17;
                    restGuns();
                    G7 = true;
                    this.gun = "G7 Scout";
                } else if (赫姆洛克() >= confidence && !赫姆洛克) {
                    System.out.println("hemuloke");
                    gunMode = 8;
                    restGuns();
                    赫姆洛克 = true;
                    this.gun = "Hemlok Burst";
                } else if (猎兽() >= confidence && !猎兽) {
                    System.out.println("lieshou");
                    gunMode = 7;
                    restGuns();
                    猎兽 = true;
                    this.gun = "Prowler Burst PDW";
                } else if (L_Star() >= confidence && !L_Star) {
                    System.out.println("L_Star");
                    gunMode = 11;
                    restGuns();
                    L_Star = true;
                    this.gun = "L_Star EMG";
                } else if (fuZhuShouQiang() >= confidence && !fuZhuShouQiang) {
                    System.out.println("fuZhuShouQiang");
                    gunMode = 9; //same as p2020
                    restGuns();
                    fuZhuShouQiang = true;
                    this.gun = "Wingman";
                }
            }

            //write to file only when the gun is changed
            if (tempGunMode != gunMode) {
                tempGunMode = gunMode; //update the gunMode
                write_to_file(tempGunMode); //write to file
                playBeep(); //play beep sound
                System.out.println("write to file");
            }

            if (!on_or_off) { //if the program is closed, break the loop
                restGuns();
                break; //break the loop
            }
        }
    }


    //rest all weapons to false
    public void restGuns() {
        r99 = false;
        r301 = false;
        转换者 = false;
        电能冲锋枪 = false;
        喷火 = false;
        vk = false;
        re_45 = false;
        暴走 = false;
        p2020 = false;
        哈沃克 = false;
        专注 = false;
        car = false;
        car2 = false;
        G7 = false;
        赫姆洛克 = false;
        猎兽 = false;
        L_Star = false;
        fuZhuShouQiang = false;
        dead = false;
        gameMenu = false;
        lobby = false;
        looting = false;
        bag = false;
        setting = false;
        blackMarket = false;
    }

    public double vk() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat vk = Imgcodecs.imread("weapon/" + screenResolution + "/vk.jpg");
        Imgproc.matchTemplate(game, vk, outputImage, machMethod);//
        Core.MinMaxLocResult Vk = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return Vk.maxVal;
    }

    public double r301() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat r301 = Imgcodecs.imread("weapon/" + screenResolution + "/r301.jpg");
        Imgproc.matchTemplate(game, r301, outputImage, machMethod);//
        Core.MinMaxLocResult R301 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return R301.maxVal;
    }

    public double car() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat car = Imgcodecs.imread("weapon/" + screenResolution + "/car.jpg");
        Imgproc.matchTemplate(game, car, outputImage, machMethod);//
        Core.MinMaxLocResult CAR = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return CAR.maxVal;
    }

    public double G7() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat g7 = Imgcodecs.imread("weapon/" + screenResolution + "/g7.jpg");
        Imgproc.matchTemplate(game, g7, outputImage, machMethod);//
        Core.MinMaxLocResult G7 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return G7.maxVal;
    }

    public double L_Star() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat l_star = Imgcodecs.imread("weapon/" + screenResolution + "/L-Star.jpg");
        Imgproc.matchTemplate(game, l_star, outputImage, machMethod);//
        Core.MinMaxLocResult L_Star = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return L_Star.maxVal;
    }

    public double r99() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat r99 = Imgcodecs.imread("weapon/" + screenResolution + "/r99.jpg");
        Imgproc.matchTemplate(game, r99, outputImage, machMethod);//
        Core.MinMaxLocResult R99 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return R99.maxVal;
    }

    public double p2020() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat p2020 = Imgcodecs.imread("weapon/" + screenResolution + "/p2020.jpg");
        Imgproc.matchTemplate(game, p2020, outputImage, machMethod);//
        Core.MinMaxLocResult P2020 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return P2020.maxVal;
    }

    public double re_45() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat re_45 = Imgcodecs.imread("weapon/" + screenResolution + "/re-45.jpg");
        Imgproc.matchTemplate(game, re_45, outputImage, machMethod);//
        Core.MinMaxLocResult RE_45 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return RE_45.maxVal;
    }

    public double 专注() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat zhuanZhu = Imgcodecs.imread("weapon/" + screenResolution + "/zhuanZhu.jpg");
        Imgproc.matchTemplate(game, zhuanZhu, outputImage, machMethod);//
        Core.MinMaxLocResult 专注机枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 专注机枪.maxVal;
    }

    public double 哈沃克() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat hawoke = Imgcodecs.imread("weapon/" + screenResolution + "/hawoke.jpg");
        Imgproc.matchTemplate(game, hawoke, outputImage, machMethod);//
        Core.MinMaxLocResult 哈沃克步枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 哈沃克步枪.maxVal;
    }

    public double 喷火() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat fire = Imgcodecs.imread("weapon/" + screenResolution + "/fire.jpg");
        Imgproc.matchTemplate(game, fire, outputImage, machMethod);//
        Core.MinMaxLocResult 喷火轻机枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 喷火轻机枪.maxVal;
    }

    public double 暴走() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat baoZou = Imgcodecs.imread("weapon/" + screenResolution + "/baoZou.jpg");
        Imgproc.matchTemplate(game, baoZou, outputImage, machMethod);//
        Core.MinMaxLocResult 暴走冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 暴走冲锋枪.maxVal;
    }

    public double 赫姆洛克() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat hemLock = Imgcodecs.imread("weapon/" + screenResolution + "/hemLock.jpg");
        Imgproc.matchTemplate(game, hemLock, outputImage, machMethod);//
        Core.MinMaxLocResult 赫姆洛克突击步枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 赫姆洛克突击步枪.maxVal;
    }

    public double 转换者() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat zhuanZhuanZhe = Imgcodecs.imread("weapon/" + screenResolution + "/zhuanZhuanZhe.jpg");
        Imgproc.matchTemplate(game, zhuanZhuanZhe, outputImage, machMethod);//
        Core.MinMaxLocResult 转换者冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 转换者冲锋枪.maxVal;
    }

    public double 猎兽() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat lieShou = Imgcodecs.imread("weapon/" + screenResolution + "/lieShou.jpg");
        Imgproc.matchTemplate(game, lieShou, outputImage, machMethod);//
        Core.MinMaxLocResult 猎兽冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 猎兽冲锋枪.maxVal;
    }

    public double 电能冲锋枪() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat dianneng = Imgcodecs.imread("weapon/" + screenResolution + "/dianneng.jpg");
        Imgproc.matchTemplate(game, dianneng, outputImage, machMethod);//
        Core.MinMaxLocResult 电能冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 电能冲锋枪.maxVal;
    }

    public double car2() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat car2 = Imgcodecs.imread("weapon/" + screenResolution + "/car2.jpg");
        Imgproc.matchTemplate(game, car2, outputImage, machMethod);//
        Core.MinMaxLocResult Car2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return Car2.maxVal;
    }

    public double fuZhuShouQiang() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat fuZhuShouQiang = Imgcodecs.imread("weapon/" + screenResolution + "/fuZhuShouQiang.jpg");
        Imgproc.matchTemplate(game, fuZhuShouQiang, outputImage, machMethod);//
        Core.MinMaxLocResult fuZhuShouQiang2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return fuZhuShouQiang2.maxVal;
    }

    public double dead() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/deadScreenshot.jpg");
        Mat dead = Imgcodecs.imread("weapon/" + screenResolution + "/dead.jpg");
        Imgproc.matchTemplate(game, dead, outputImage, machMethod);//
        Core.MinMaxLocResult dead2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        //System.out.println("dead2.maxVal = " + dead2.maxVal);
        return dead2.maxVal;
    }

    public double dead1() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/deadScreenshot.jpg");
        Mat dead1 = Imgcodecs.imread("weapon/" + screenResolution + "/dead1.jpg");
        Imgproc.matchTemplate(game, dead1, outputImage, machMethod);//
        Core.MinMaxLocResult dead3 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return dead3.maxVal;
    }

    public double dead2() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/deadScreenshot.jpg");
        Mat dead2 = Imgcodecs.imread("weapon/" + screenResolution + "/dead2.jpg");
        Imgproc.matchTemplate(game, dead2, outputImage, machMethod);//
        Core.MinMaxLocResult dead4 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return dead4.maxVal;
    }


    public double lobby() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat notInGame = Imgcodecs.imread("weapon/" + screenResolution + "/menu.jpg");
        Imgproc.matchTemplate(game, notInGame, outputImage, machMethod);//
        Core.MinMaxLocResult notInGame2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return notInGame2.maxVal;
    }

    public double returnLobby() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat notInGame1 = Imgcodecs.imread("weapon/" + screenResolution + "/returnLobby.jpg");
        Imgproc.matchTemplate(game, notInGame1, outputImage, machMethod);//
        Core.MinMaxLocResult notInGame3 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return notInGame3.maxVal;
    }

    public double exitMenu(){
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat exitMenu = Imgcodecs.imread("weapon/" + screenResolution + "/exitToMenu.jpg");
        Imgproc.matchTemplate(game, exitMenu, outputImage, machMethod);//
        Core.MinMaxLocResult exitMenu2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return exitMenu2.maxVal;
    }

    public double bag() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/settingScreenshot.jpg");
        Mat bag = Imgcodecs.imread("weapon/" + screenResolution + "/bag.jpg");
        Imgproc.matchTemplate(game, bag, outputImage, machMethod);//
        Core.MinMaxLocResult bag2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return bag2.maxVal;
    }

    public double settings(){
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/settingScreenshot.jpg");
        Mat settings = Imgcodecs.imread("weapon/" + screenResolution + "/setting.jpg");
        Imgproc.matchTemplate(game, settings, outputImage, machMethod);//
        Core.MinMaxLocResult settings2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return settings2.maxVal;
    }

    public double BlackMarket(){
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/deadScreenshot.jpg");
        Mat BlackMarket = Imgcodecs.imread("weapon/" + screenResolution + "/blackMarket.jpg");
        Imgproc.matchTemplate(game, BlackMarket, outputImage, machMethod);//
        Core.MinMaxLocResult BlackMarket2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return BlackMarket2.maxVal;
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
