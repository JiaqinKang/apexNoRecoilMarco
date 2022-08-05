import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


public class autoMarco {
    int machMethod = Imgproc.TM_CCOEFF_NORMED;
    int gunMode;
    int tempGunMode;
    boolean on_or_off = false;
    boolean isMute = true;
    public autoMarco() {
        //scanner config file for gun mode

        JFrame frame = new JFrame("Apex腰射全自动宏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.jpg").getImage());
        frame.setSize(350, 350);
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
        //JLabel gun = new JLabel("枪模式");

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(mute);
        panel1.add(unMute);

        button1.setBackground(Color.white);
        button2.setBackground(Color.red);
        mute.setBackground(Color.red);
        unMute.setBackground(Color.white);
        //panel1.add(gun);

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

        //pop up message when start the program
        JOptionPane.showMessageDialog(frame, """
                操作说明:
                游戏中鼠标灵敏度为1.6，鼠标加速度关闭，罗技驱动——>指针设置——>报告率改为1000，加速关闭
                哈沃克和轻型机枪必须有涡轮增压器,Numlock开关宏
                """
        );

        frame.setVisible(true);


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

        while (true) {
            if (on_or_off){
                scan();
            }
        }

    }

    public void write_to_file(int i) {
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

    public void scan() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        while (true) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            BufferedImage image;
            try {
                Robot robot = new Robot();
                image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "jpg", new File("weapon/screenshot.jpg"));
            } catch (IOException | AWTException e) {
                throw new RuntimeException(e);
            }

            if (r99() > 0.9) {
                System.out.println("r99");
                gunMode = 1;
            } else if (r301() > 0.9) {
                System.out.println("r301");
                gunMode = 2;
            } else if (转换者() > 0.9) {
                System.out.println("zhuanHuanZhe");
                gunMode = 3;
            }else if (电能冲锋枪() > 0.9) {
                System.out.println("dianNeng");
                gunMode = 4;
            } else if (喷火() > 0.9) {
                System.out.println("penhuo");
                gunMode = 10;
            } else if (vk() > 0.9) {
                System.out.println("vk");
                gunMode = 6;
            } else if (re_45() > 0.9) {
                System.out.println("re_45");
                gunMode = 13;
            } else if (p2020() > 0.9) {
                System.out.println("p2020");
                gunMode = 9;
            } else if (暴走() > 0.9) {
                System.out.println("baozou");
                gunMode = 19;
            } else if (哈沃克() > 0.9) {
                System.out.println("hawoke");
                gunMode = 16;
            } else if (专注() > 0.9) {
                System.out.println("zhuanzhu");
                gunMode = 5;
            } else if (car() > 0.9) {
                System.out.println("car");
                gunMode = 20;
            } else if (G7() > 0.9) {
                System.out.println("G7");
                gunMode = 17;
            } else if (赫姆洛克() > 0.9){
                System.out.println("hemuloke");
                gunMode = 8;
            } else if (猎兽() > 0.9){
                System.out.println("lieshou");
                gunMode = 7;
            } else if (L_Star() > 0.9) {
                System.out.println("L_Star");
                gunMode = 11;
            } else {
                System.out.println("No weapon");
            }

            if (tempGunMode != gunMode) {
                tempGunMode = gunMode;
                write_to_file(tempGunMode);
                playBeep();
                System.out.println("write to file");
            }
            if (!on_or_off){
                break;
            }
        }
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
    public void playBeep(){
        if (!isMute) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    //run the program
    public static void main(String[] args) {
        new autoMarco();
    }

}
