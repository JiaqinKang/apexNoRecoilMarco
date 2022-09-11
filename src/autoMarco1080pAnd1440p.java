import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class autoMarco1080pAnd1440p {
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode;
    int tempGunMode;
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
    String gun = "null";
    File from = new File("ScriptSeason14腰射.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\ScriptSeason14.lua");

    //check  system resolution
    int SystemWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int SystemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //language
    String language = "中文";

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
                gun.setText("当前枪模式：" + this.gun);
            } else {
                gun.setText("Current gun：" + this.gun);
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
                
                English:
                Mouse sensitivity in game is 1.6, mouse acceleration is off, Logitech driver->pointer settings->report rate is 1000, acceleration is off
                Havoc and light machine gun must have turbocharger, Numlock switch for on/off macro, hold the right mouse button to start the macro when shooting);
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

        System.out.println(SystemWidth + " " + SystemHeight);

        if (SystemHeight == 1080) {
            //scan 1080p
        } else if (SystemHeight == 1440) {
            //scan 1440p
        }

        while (true) {
            if (on_or_off){
                scan();
            }
        }

    }

    private void write_to_file(int i) {
        //write lua file to C:\Users\Public\Downloads
        String path = "C:\\Users\\Public\\Downloads\\";
        String file_name = "123.lua";
        String file_path = path + file_name;
        String file_content = "qx1_1 = GunCombination1_1[" + i +"]";
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

    private void scan() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        while (true) {

            // Capture a particular area on the screen for 1440p screen

            int x= (SystemWidth*3/4);
            //540

            int height = (SystemHeight/4)/2;

            int y = (SystemHeight*3/4)+height;

            int width =(SystemWidth/4);





            //capture the weapon selection area
            try {
                Robot robot = new Robot();
                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height)); //capture the area
                ImageIO.write(image, "jpg", new File("weapon/screenshot.jpg")); //save the screenshot to the file
            } catch (IOException | AWTException e) {
                throw new RuntimeException(e);
            }

            double confidence = 0.93; //confidence level

            if (!r99 && r99() > confidence) {
                System.out.println("r99");
                gunMode = 1;
                restGuns();//reset all guns
                r99 = true; //set r99 to true to avoid multiple detection
                this.gun = "R-99 SMG";
            } else if ( !r301 &&  r301() > confidence) {
                System.out.println("r301");
                gunMode = 2;
                restGuns();
                r301 = true;
                this.gun = "R-301 Carbine";
            } else if (转换者() > confidence && !转换者) {
                System.out.println("zhuanHuanZhe");
                gunMode = 3;
                restGuns();
                转换者 = true;
                this.gun = "Alternator SMG";
            }else if (电能冲锋枪() > confidence && !电能冲锋枪) {
                System.out.println("dianNeng");
                gunMode = 4;
                restGuns();
                电能冲锋枪 = true;
                this.gun = "Volt SMG";
            } else if (喷火() > confidence && !喷火) {
                System.out.println("penhuo");
                gunMode = 10;
                restGuns();
                喷火 = true;
                this.gun = "M600 Spitfire";
            } else if (vk() > confidence && !vk) {
                System.out.println("vk");
                gunMode = 6;
                restGuns();
                vk = true;
                this.gun = "VK-47 Flatline";
            } else if (re_45() > confidence && !re_45) {
                System.out.println("re_45");
                gunMode = 13;
                restGuns();
                re_45 = true;
                this.gun = "RE_45 Auto";
            }  else if (暴走() > confidence && !暴走) {
                System.out.println("baozou");
                gunMode = 19;
                restGuns();
                暴走 = true;
                this.gun = "Rampage LMG";
            } else if (p2020() > confidence && !p2020) {
                System.out.println("p2020");
                gunMode = 9;
                restGuns();
                p2020 = true;
                this.gun = "P2020";
            } else if (哈沃克() > confidence && !哈沃克) {
                System.out.println("hawoke");
                gunMode = 16;
                restGuns();
                哈沃克 = true;
                this.gun = "Havoc Rifle";
            } else if (专注() > confidence && !专注) {
                System.out.println("zhuanzhu");
                gunMode = 5;
                restGuns();
                专注 = true;
                this.gun = "Devotion LMG";
            } else if (car() > confidence && !car) {
                System.out.println("car");
                gunMode = 20;
                restGuns();
                //same recoil now both set to true for debug
                car = true;
                car2 = true;
                this.gun = "C.A.R SMG";
            } else if (car2() > confidence && !car2) {
                System.out.println("car2");
                gunMode = 20;
                restGuns();
                //same recoil now both set to true for debug
                car = true;
                car2 = true;
                this.gun = "C.A.R SMG";
            } else if (G7() > confidence && !G7) {
                System.out.println("G7");
                gunMode = 17;
                restGuns();
                G7 = true;
                this.gun = "G7 Scout";
            } else if (赫姆洛克() > confidence && !赫姆洛克) {
                System.out.println("hemuloke");
                gunMode = 8;
                restGuns();
                赫姆洛克 = true;
                this.gun = "Hemlok Burst";
            } else if (猎兽() > confidence && !猎兽) {
                System.out.println("lieshou");
                gunMode = 7;
                restGuns();
                猎兽 = true;
                this.gun = "Prowler Burst PDW";
            } else if (L_Star() > confidence && !L_Star) {
                System.out.println("L_Star");
                gunMode = 11;
                restGuns();
                L_Star = true;
                this.gun = "L_Star EMG";
            } else if (fuZhuShouQiang() > confidence && !fuZhuShouQiang){
                System.out.println("fuZhuShouQiang");
                gunMode = 9; //same as p2020
                restGuns();
                fuZhuShouQiang = true;
                this.gun = "Wingman";
            }

            //write to file only when the gun is changed
            if (tempGunMode != gunMode) {
                tempGunMode = gunMode; //update the gunMode
                write_to_file(tempGunMode); //write to file
                playBeep(); //play beep sound
                System.out.println("write to file");
            }
            if (!on_or_off){ //if the program is closed, break the loop
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
    }
    public double vk() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat vk = Imgcodecs.imread("weapon/vk.jpg");
        Imgproc.matchTemplate(game, vk, outputImage, machMethod);//
        Core.MinMaxLocResult Vk = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return Vk.maxVal;
    }
    public double r301() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat r301 = Imgcodecs.imread("weapon/r301.jpg");
        Imgproc.matchTemplate(game, r301, outputImage, machMethod);//
        Core.MinMaxLocResult R301 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return R301.maxVal;
    }
    public double car() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat car = Imgcodecs.imread("weapon/car.jpg");
        Imgproc.matchTemplate(game, car, outputImage, machMethod);//
        Core.MinMaxLocResult CAR = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return CAR.maxVal;
    }
    public double G7() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat g7 = Imgcodecs.imread("weapon/g7.jpg");
        Imgproc.matchTemplate(game, g7, outputImage, machMethod);//
        Core.MinMaxLocResult G7 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return G7.maxVal;
    }
    public double L_Star() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat l_star = Imgcodecs.imread("weapon/L-Star.jpg");
        Imgproc.matchTemplate(game, l_star, outputImage, machMethod);//
        Core.MinMaxLocResult L_Star = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return L_Star.maxVal;
    }
    public double r99() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat r99 = Imgcodecs.imread("weapon/r99.jpg");
        Imgproc.matchTemplate(game, r99, outputImage, machMethod);//
        Core.MinMaxLocResult R99 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return R99.maxVal;
    }
    public double p2020() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat p2020 = Imgcodecs.imread("weapon/p2020.jpg");
        Imgproc.matchTemplate(game, p2020, outputImage, machMethod);//
        Core.MinMaxLocResult P2020 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return P2020.maxVal;
    }
    public double re_45() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat re_45 = Imgcodecs.imread("weapon/re-45.jpg");
        Imgproc.matchTemplate(game, re_45, outputImage, machMethod);//
        Core.MinMaxLocResult RE_45 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return RE_45.maxVal;
    }
    public double 专注() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat zhuanZhu = Imgcodecs.imread("weapon/zhuanZhu.jpg");
        Imgproc.matchTemplate(game, zhuanZhu, outputImage, machMethod);//
        Core.MinMaxLocResult 专注机枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 专注机枪.maxVal;
    }
    public double 哈沃克() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat hawoke = Imgcodecs.imread("weapon/hawoke.jpg");
        Imgproc.matchTemplate(game, hawoke, outputImage, machMethod);//
        Core.MinMaxLocResult 哈沃克步枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 哈沃克步枪.maxVal;
    }
    public double 喷火() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat fire = Imgcodecs.imread("weapon/fire.jpg");
        Imgproc.matchTemplate(game, fire, outputImage, machMethod);//
        Core.MinMaxLocResult 喷火轻机枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 喷火轻机枪.maxVal;
    }
    public double 暴走() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat baoZou = Imgcodecs.imread("weapon/baoZou.jpg");
        Imgproc.matchTemplate(game, baoZou, outputImage, machMethod);//
        Core.MinMaxLocResult 暴走冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 暴走冲锋枪.maxVal;
    }
    public double 赫姆洛克() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat hemLock = Imgcodecs.imread("weapon/hemLock.jpg");
        Imgproc.matchTemplate(game, hemLock, outputImage, machMethod);//
        Core.MinMaxLocResult 赫姆洛克突击步枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 赫姆洛克突击步枪.maxVal;
    }
    public double 转换者() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat zhuanZhuanZhe = Imgcodecs.imread("weapon/zhuanZhuanZhe.jpg");
        Imgproc.matchTemplate(game, zhuanZhuanZhe, outputImage, machMethod);//
        Core.MinMaxLocResult 转换者冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 转换者冲锋枪.maxVal;
    }
    public double 猎兽() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat lieShou = Imgcodecs.imread("weapon/lieShou.jpg");
        Imgproc.matchTemplate(game, lieShou, outputImage, machMethod);//
        Core.MinMaxLocResult 猎兽冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 猎兽冲锋枪.maxVal;
    }
    public double 电能冲锋枪() {
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat dianneng = Imgcodecs.imread("weapon/dianneng.jpg");
        Imgproc.matchTemplate(game, dianneng, outputImage, machMethod);//
        Core.MinMaxLocResult 电能冲锋枪 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return 电能冲锋枪.maxVal;
    }
    public double car2(){
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat car2 = Imgcodecs.imread("weapon/car2.jpg");
        Imgproc.matchTemplate(game, car2, outputImage, machMethod);//
        Core.MinMaxLocResult Car2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return Car2.maxVal;
    }

    public double fuZhuShouQiang(){
        Mat outputImage = new Mat();
        Mat game = Imgcodecs.imread("weapon/screenshot.jpg");
        Mat fuZhuShouQiang = Imgcodecs.imread("weapon/fuZhuShouQiang.jpg");
        Imgproc.matchTemplate(game, fuZhuShouQiang, outputImage, machMethod);//
        Core.MinMaxLocResult fuZhuShouQiang2 = Core.minMaxLoc(outputImage);//find the max value and the location of the max value
        return fuZhuShouQiang2.maxVal;
    }



    public void playBeep(){
        if (!isMute) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    //run the program
    public static void main(String[] args) {
        new autoMarco1080pAnd1440p();
    }

}
