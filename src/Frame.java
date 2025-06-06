import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private JTextArea textarea;
    private JTextField textField;
    private JButton writeButton;

public Frame()
{
    setTitle("Writer");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

textarea=new JTextArea();
JScrollPane scrollPane=new JScrollPane(textarea);
scrollPane.setBounds(20,20,350,150);
add(scrollPane);

textField=new JTextField();
textField.setBounds(20,180,200,30);
add(textField);

writeButton=new JButton("write");
writeButton.setBounds(200,200,100,30);
add(writeButton);

writeButton.addActionListener(new ActionListener()
{
@Override
    public void actionPerformed(ActionEvent e)
{
String threadName=textField.getText();
if(!threadName.isEmpty())
{
Thread t = new WriterThread(threadName);
t.start();}
else {
    JOptionPane.showMessageDialog(null, "Please enter a thread name.");
}
}
});
        }

        class WriterThread extends Thread{
    public WriterThread(String name){
        super(name);
    }
    @Override
    public void run()
    {
        for(int i=1;i<=5;i++)
        {
            String msg = getName() + "-msg nr" + i+"\n";
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(()->textarea.append(msg));
        }
    }
        }
        }