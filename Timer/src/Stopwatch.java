import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel label = new JLabel();

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime +=1000;
            hours = elapsedTime/3600000;
            minutes = (elapsedTime/60000)%60;
            seconds = (elapsedTime/1000)%60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            label.setText(hours_string + " : " +minutes_string + " : " +seconds_string);

        }
    });

    Stopwatch(){
        label.setText(seconds_string + " : " + minutes_string +": " + hours_string);
        label.setBounds(100, 100,300,100);
        label.setFont(new Font("Verdana", Font.PLAIN, 35));
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setBackground(Color.green);


        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
             if (e.getSource()==startButton) {
                 if (!started) {
                     started = true;
                     startButton.setText("PAUSE");
                     startButton.setBackground(Color.red);
                     start();
                 } else {
                     started = false;
                     startButton.setText("START");
                     startButton.setBackground(Color.green);
                     stop();
                 }
             }

                 if (e.getSource()==resetButton){
                     started = false;
                     startButton.setText("START");
                     reset();
                 }
    }

    void start(){
         timer.start();
    }

    void reset(){
        timer.stop();
        elapsedTime =0;
        seconds =0;
        minutes = 0;
        hours = 0;

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        label.setText(hours_string + " : " +minutes_string + " : " +seconds_string);

    }

    void stop() {
        timer.stop();
    }

}
