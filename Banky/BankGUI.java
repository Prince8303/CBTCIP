import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI {
    private Bank bank;
    private JFrame frame;
    private JTextField accountNumberField;
    private JTextField ownerField;
    private JTextField depositField;
    private JTextField withdrawField;
    private JTextField transferAmountField;
    private JTextField targetAccountField;
    private JTextArea displayArea;

    public BankGUI() {
        bank = Bank.loadAccounts(); // Load existing accounts
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Banky");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(135, 206, 235);
                Color color2 = new Color(25, 25, 112);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setFont(labelFont);
        lblAccountNumber.setForeground(Color.WHITE);
        panel.add(lblAccountNumber, gbc);

        accountNumberField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(accountNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblOwner = new JLabel("Owner:");
        lblOwner.setFont(labelFont);
        lblOwner.setForeground(Color.WHITE);
        panel.add(lblOwner, gbc);

        ownerField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(ownerField, gbc);

        JButton btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setFont(buttonFont);
        btnCreateAccount.setBackground(new Color(0, 128, 128));
        btnCreateAccount.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnCreateAccount, gbc);
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel lblDeposit = new JLabel("Deposit Amount:");
        lblDeposit.setFont(labelFont);
        lblDeposit.setForeground(Color.WHITE);
        panel.add(lblDeposit, gbc);

        depositField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(depositField, gbc);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setFont(buttonFont);
        btnDeposit.setBackground(new Color(0, 128, 0));
        btnDeposit.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnDeposit, gbc);
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        JLabel lblWithdraw = new JLabel("Withdraw Amount:");
        lblWithdraw.setFont(labelFont);
        lblWithdraw.setForeground(Color.WHITE);
        panel.add(lblWithdraw, gbc);

        withdrawField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(withdrawField, gbc);

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setFont(buttonFont);
        btnWithdraw.setBackground(new Color(255, 140, 0));
        btnWithdraw.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(btnWithdraw, gbc);
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        JLabel lblTransferAmount = new JLabel("Transfer Amount:");
        lblTransferAmount.setFont(labelFont);
        lblTransferAmount.setForeground(Color.WHITE);
        panel.add(lblTransferAmount, gbc);

        transferAmountField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(transferAmountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel lblTargetAccount = new JLabel("Target Account:");
        lblTargetAccount.setFont(labelFont);
        lblTargetAccount.setForeground(Color.WHITE);
        panel.add(lblTargetAccount, gbc);

        targetAccountField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(targetAccountField, gbc);

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setFont(buttonFont);
        btnTransfer.setBackground(new Color(70, 130, 180));
        btnTransfer.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(btnTransfer, gbc);
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        JButton btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.setFont(buttonFont);
        btnCheckBalance.setBackground(new Color(255, 69, 0));
        btnCheckBalance.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(btnCheckBalance, gbc);
        btnCheckBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        displayArea = new JTextArea(5, 30); // Limit height to 5 rows
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayArea.setBackground(new Color(230, 230, 250));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(800, 100)); // Set preferred size for the log
        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createAccount() {
        String accountNumber = accountNumberField.getText();
        String owner = ownerField.getText();
        double initialDeposit;

        try {
            initialDeposit = Double.parseDouble(depositField.getText());
            bank.createAccount(accountNumber, owner, initialDeposit);
            displayArea.append("Account created: " + owner + " (" + accountNumber + ") with initial deposit: " + initialDeposit + "\n");
            bank.saveAccounts();
            clearFields();
        } catch (NumberFormatException e) {
            displayArea.append("Invalid initial deposit amount.\n");
        }
    }

    private void deposit() {
        String accountNumber = accountNumberField.getText();
        double amount;

        try {
            amount = Double.parseDouble(depositField.getText());
            Account account = bank.getAccount(accountNumber);
            if (account != null && account.deposit(amount)) {
                displayArea.append("Deposited " + amount + " to account " + accountNumber + "\n");
                bank.saveAccounts();
                clearFields();
            } else {
                displayArea.append("Deposit failed.\n");
            }
        } catch (NumberFormatException e) {
            displayArea.append("Invalid deposit amount.\n");
        }
    }

    private void withdraw() {
        String accountNumber = accountNumberField.getText();
        double amount;

        try {
            amount = Double.parseDouble(withdrawField.getText());
            Account account = bank.getAccount(accountNumber);
            if (account != null && account.withdraw(amount)) {
                displayArea.append("Withdrew " + amount + " from account " + accountNumber + "\n");
                bank.saveAccounts();
                clearFields();
            } else {
                displayArea.append("Withdrawal failed.\n");
            }
        } catch (NumberFormatException e) {
            displayArea.append("Invalid withdrawal amount.\n");
        }
    }

    private void transfer() {
        String accountNumber = accountNumberField.getText();
        String targetAccountNumber = targetAccountField.getText();
        double amount;

        try {
            amount = Double.parseDouble(transferAmountField.getText());
            Account account = bank.getAccount(accountNumber);
            Account targetAccount = bank.getAccount(targetAccountNumber);
            if (account != null && targetAccount != null && account.transfer(targetAccount, amount)) {
                displayArea.append("Transferred " + amount + " from account " + accountNumber + " to " + targetAccountNumber + "\n");
                bank.saveAccounts();
                clearFields();
            } else {
                displayArea.append("Transfer failed.\n");
            }
        } catch (NumberFormatException e) {
            displayArea.append("Invalid transfer amount.\n");
        }
    }

    private void checkBalance() {
        String accountNumber = accountNumberField.getText();
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            displayArea.append("Balance for account " + accountNumber + ": " + account.getBalance() + "\n");
        } else {
            displayArea.append("Account not found.\n");
        }
    }

    private void clearFields() {
        accountNumberField.setText("");
        ownerField.setText("");
        depositField.setText("");
        withdrawField.setText("");
        transferAmountField.setText("");
        targetAccountField.setText("");
    }

    public static void main(String[] args) {
        new BankGUI();
    }
}
