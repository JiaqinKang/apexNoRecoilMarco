import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;


public class manualMarco {
    Boolean isMute = false;

    File from = new File("ScriptSeason14.lua");
    File to = new File("C:\\Users\\Public\\Downloads\\ScriptSeason14.lua");

    public manualMarco() {

        JFrame frame = new JFrame("Apex腰射手动宏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.jpg").getImage());
        frame.setSize(275, 700);
        frame.setResizable(false);
        //frame in the center of screen
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        JPanel panel1 = new JPanel();

        panel1.setLayout(new GridLayout(19, 0));

        //        -- R-301步枪 = 2
        //        -- VK平行步枪 = 6
        //        -- R-99冲锋枪 = 1
        //        -- 转换者冲锋枪 = 3
        //        -- 电能冲锋枪 = 4
        //        -- 专注轻机枪 = 5
        //        -- 猎兽冲锋枪 = 7
        //        -- 赫姆洛克（单点）=8
        //        -- P2020 = 9
        //        -- 喷火轻机枪 = 10
        //        -- L-star能量机枪 = 11
        //        -- P202 = 12
        //        -- RE-45自动手枪 = 13
        //        -- 哈沃克步枪(有涡轮) = 16
        //        -- G7步枪 = 17
        //        -- 暴走 =19
        //        -- CAR冲锋枪 = 23

        //create buttons
        JButton R301 = new JButton("R-301步枪");
        JButton VK = new JButton("VK平行步枪");
        JButton R99 = new JButton("R-99");
        JButton 转换者冲锋枪 = new JButton("转换者冲锋枪");
        JButton 电能冲锋枪 = new JButton("电能冲锋枪");
        JButton 专注轻机枪 = new JButton("专注轻机枪");
        JButton 猎兽冲锋枪 = new JButton("猎兽冲锋枪");
        JButton 赫姆洛克 = new JButton("赫姆洛克");
        JButton 喷火轻机枪 = new JButton("喷火轻机枪");
        JButton L_star能量机枪 = new JButton("L-star");
        JButton P202 = new JButton("P202");
        JButton RE_45自动手枪 = new JButton("RE-45自动手枪");
        JButton 哈沃克步枪 = new JButton("哈沃克步枪");
        JButton G7步枪 = new JButton("G7步枪");
        JButton 暴走 = new JButton("暴走");
        JButton CAR冲锋枪 = new JButton("CAR冲锋枪");
        JButton mute = new JButton("静音");
        JButton unMute = new JButton("取消静音");
        JButton release = new JButton("释放脚本");

        R301.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        VK.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        R99.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        转换者冲锋枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        电能冲锋枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        专注轻机枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        猎兽冲锋枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        赫姆洛克.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        喷火轻机枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        L_star能量机枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        P202.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        RE_45自动手枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        哈沃克步枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        G7步枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        暴走.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        CAR冲锋枪.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        mute.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        unMute.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        release.setFont(new Font("微软雅黑", Font.PLAIN, 30));




        //add buttons to panel1
        panel1.add(R301);
        panel1.add(VK);
        panel1.add(R99);
        panel1.add(转换者冲锋枪);
        panel1.add(电能冲锋枪);
        panel1.add(专注轻机枪);
        panel1.add(猎兽冲锋枪);
        panel1.add(赫姆洛克);
        panel1.add(喷火轻机枪);
        panel1.add(L_star能量机枪);
        panel1.add(P202);
        panel1.add(RE_45自动手枪);
        panel1.add(哈沃克步枪);
        panel1.add(G7步枪);
        panel1.add(暴走);
        panel1.add(CAR冲锋枪);
        panel1.add(mute);
        panel1.add(unMute);
        panel1.add(release);

        //add panel1 to frame
        frame.add(panel1);

        VK.setBackground(Color.WHITE);
        R301.setBackground(Color.WHITE);
        R99.setBackground(Color.WHITE);
        转换者冲锋枪.setBackground(Color.WHITE);
        电能冲锋枪.setBackground(Color.WHITE);
        专注轻机枪.setBackground(Color.WHITE);
        猎兽冲锋枪.setBackground(Color.WHITE);
        赫姆洛克.setBackground(Color.WHITE);
        喷火轻机枪.setBackground(Color.WHITE);
        L_star能量机枪.setBackground(Color.WHITE);
        P202.setBackground(Color.WHITE);
        RE_45自动手枪.setBackground(Color.WHITE);
        哈沃克步枪.setBackground(Color.WHITE);
        G7步枪.setBackground(Color.WHITE);
        暴走.setBackground(Color.WHITE);
        CAR冲锋枪.setBackground(Color.WHITE);
        mute.setBackground(Color.WHITE);
        unMute.setBackground(Color.WHITE);
        release.setBackground(Color.WHITE);


        if (isMute){
            mute.setBackground(Color.GREEN);
            unMute.setBackground(Color.WHITE);
        }
        else if (!isMute){
            unMute.setBackground(Color.GREEN);
            mute.setBackground(Color.WHITE);
        }

        //if release button is pressed then release the script to local
        release.addActionListener(e -> {
            try {
                copyFileUsingJava7Files(from, to);
                //disable button
                release.setEnabled(false);
                //show message
                JOptionPane.showMessageDialog(frame, "脚本文件已释放到本地");
                release.setText("脚本文件OK");
                release.setBackground(Color.green);
            } catch (IOException e1) {
                e1.printStackTrace();
                //show message
                JOptionPane.showMessageDialog(frame, "脚本文件释放失败");
            }
        });

        //action listener for buttons
        R301.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                R301.setBackground(Color.GREEN);
                VK.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(2);
                //make a beep sound
                playBeep();
            }
        });

        VK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                VK.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(6);
                //make a beep sound
                playBeep();
            }
        });

        R99.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                R99.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(1);
                //make a beep sound
                playBeep();
            }
        });

        转换者冲锋枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                转换者冲锋枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(3);
                //make a beep sound
                playBeep();
            }
        });

        电能冲锋枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                电能冲锋枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(4);
                //make a beep sound
                playBeep();
            }
        });

        专注轻机枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                专注轻机枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(5);
                //make a beep sound
                playBeep();
            }
        });

        猎兽冲锋枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                猎兽冲锋枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(7);
                //make a beep sound
                playBeep();
            }
        });

        赫姆洛克.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                赫姆洛克.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(8);
                //make a beep sound
                playBeep();
            }
        });

        喷火轻机枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                喷火轻机枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(10);
                //make a beep sound
                playBeep();
            }
        });

        L_star能量机枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                L_star能量机枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(11);
                //make a beep sound
                playBeep();
            }
        });

        P202.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                P202.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(9);
                //make a beep sound
                playBeep();
            }
        });

        RE_45自动手枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                RE_45自动手枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(13);
                //make a beep sound
                playBeep();
            }
        });

        哈沃克步枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                哈沃克步枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(16);
                //make a beep sound
                playBeep();
            }
        });

        G7步枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                G7步枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(17);
                //make a beep sound
                playBeep();
            }
        });

        暴走.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                暴走.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                CAR冲锋枪.setBackground(Color.WHITE);
                //write to file
                write_to_file(19);
                //make a beep sound
                playBeep();
            }
        });

        CAR冲锋枪.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //change button color
                CAR冲锋枪.setBackground(Color.GREEN);
                R301.setBackground(Color.WHITE);
                R99.setBackground(Color.WHITE);
                VK.setBackground(Color.WHITE);
                转换者冲锋枪.setBackground(Color.WHITE);
                电能冲锋枪.setBackground(Color.WHITE);
                专注轻机枪.setBackground(Color.WHITE);
                猎兽冲锋枪.setBackground(Color.WHITE);
                赫姆洛克.setBackground(Color.WHITE);
                喷火轻机枪.setBackground(Color.WHITE);
                L_star能量机枪.setBackground(Color.WHITE);
                P202.setBackground(Color.WHITE);
                RE_45自动手枪.setBackground(Color.WHITE);
                哈沃克步枪.setBackground(Color.WHITE);
                G7步枪.setBackground(Color.WHITE);
                暴走.setBackground(Color.WHITE);
                //write to file
                write_to_file(20);
                //make a beep sound
                playBeep();
            }
        });

        //if mute button is pressed then mute the sound
        mute.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                isMute = true;
                //change button color
                mute.setBackground(Color.GREEN);
                unMute.setBackground(Color.WHITE);
            }
        });
        unMute.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                isMute = false;
                //change button color
                unMute.setBackground(Color.GREEN);
                mute.setBackground(Color.WHITE);
            }
        });

        //pop up message when start the program
        JOptionPane.showMessageDialog(frame, "操作说明:\n"
                + "游戏中鼠标灵敏度为1.6，鼠标加速度关闭，罗技驱动——>指针设置——>报告率改为1000，加速关闭\n"
                + "哈沃克和轻型机枪必须有涡轮增压器,Numlock开关宏\n"
        );

        frame.setVisible(true);

    }

    private void copyFileUsingJava7Files(File source, File dest) throws IOException {
        //if file already exist then delete it first
        if (dest.exists()) {
            dest.delete();//delete file
        }
        //copy file
        Files.copy(source.toPath(), dest.toPath());//copy file
    }
    public void playBeep(){
        if (isMute == false) {
            Toolkit.getDefaultToolkit().beep();
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

    public static void main(String[] args) {
        new manualMarco();
    }
}
