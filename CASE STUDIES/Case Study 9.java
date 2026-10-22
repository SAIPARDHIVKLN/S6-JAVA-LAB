
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegisterForm {
    public static void main(String[] args) {
        JFrame f = new JFrame("Student Registration");
        f.setLayout(new FlowLayout());

        JTextField name = new JTextField(14);
        JComboBox<String> br = new JComboBox<>(
            new String[]{"CSE", "IT", "ECE"}
        );

        JRadioButton m = new JRadioButton("Male", true);
        JRadioButton fe = new JRadioButton("Female");
        ButtonGroup g = new ButtonGroup();
        g.add(m);
        g.add(fe);

        JCheckBox hostel = new JCheckBox("Hostel Required");

        JButton sub = new JButton("Submit");
        JButton clear = new JButton("Clear");

        String[] cols = {"Name", "Branch", "Gender", "Hostel"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(380, 100));

        f.add(new JLabel("Name"));
        f.add(name);
        f.add(new JLabel("Branch"));
        f.add(br);
        f.add(m);
        f.add(fe);
        f.add(hostel);
        f.add(sub);
        f.add(clear);
        f.add(sp);

        sub.addActionListener(e -> {
            String nm = name.getText().trim();

            if (nm.isEmpty()) {
                JOptionPane.showMessageDialog(
                    f, "Name cannot be empty"
                );
            } else {
                model.addRow(new Object[]{
                    nm,
                    br.getSelectedItem(),
                    m.isSelected() ? "Male" : "Female",
                    hostel.isSelected() ? "Yes" : "No"
                });
            }
        });

        clear.addActionListener(e -> {
            name.setText("");
            br.setSelectedIndex(0);
            m.setSelected(true);
            hostel.setSelected(false);
            table.clearSelection();
        });

        f.setSize(450, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
