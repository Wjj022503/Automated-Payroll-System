/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;


import common.rmiinterface;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author wjing
 */
public class payrollGUI extends javax.swing.JFrame {
    private CardLayout cardLayout;
    private boolean isShowPassword;    
    private boolean isUsernamePlaceholder = true;    
    private boolean isPasswordPlaceholder = true;


    /**
     * Creates new form payrollGUI
     */
    public payrollGUI(){
        initComponents();
        this.cardLayout = (CardLayout) getContentPane().getLayout();
        addPlaceholderStyle(LoginUsernameInput);        
        addPlaceholderStyle(LoginPasswordInput);
    }

    public void addPlaceholderStyle(JTextField textField){
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }
    
    public void removePlaceholderStyle(JTextField textField){
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN|Font.BOLD);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginPage = new javax.swing.JPanel();
        LoginButton = new javax.swing.JButton();
        LoginUsernameInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LoginButton1 = new javax.swing.JButton();
        ShowPasswordCheckBox = new javax.swing.JCheckBox();
        LoginPasswordInput = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        LoginButton.setText("Login");
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gotoPayRollPage(evt);
            }
        });

        LoginUsernameInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginUsernameInput.setText("Username");
        LoginUsernameInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LoginUsernameInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                LoginUsernameInputFocusLost(evt);
            }
        });

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        LoginButton1.setText("Register");
        LoginButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gotoRegisterPage(evt);
            }
        });

        ShowPasswordCheckBox.setText("Show Password");
        ShowPasswordCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ShowPasswordCheckBoxStateChanged(evt);
            }
        });

        LoginPasswordInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginPasswordInput.setText("Password");
        LoginPasswordInput.setEchoChar('\u0000');
        LoginPasswordInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LoginPasswordInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                LoginPasswordInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout LoginPageLayout = new javax.swing.GroupLayout(LoginPage);
        LoginPage.setLayout(LoginPageLayout);
        LoginPageLayout.setHorizontalGroup(
            LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LoginPageLayout.createSequentialGroup()
                    .addGap(261, 261, 261)
                    .addComponent(LoginButton))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPageLayout.createSequentialGroup()
                    .addGap(391, 391, 391)
                    .addComponent(LoginButton1)))
            .addGroup(LoginPageLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(56, 56, 56)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ShowPasswordCheckBox)
                    .addComponent(LoginPasswordInput)
                    .addComponent(LoginUsernameInput)))
        );
        LoginPageLayout.setVerticalGroup(
            LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPageLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(39, 39, 39)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LoginPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(ShowPasswordCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginButton)
                    .addComponent(LoginButton1))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        getContentPane().add(LoginPage, "card3");

        jLabel3.setText("This is Payroll Page");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(360, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        jLabel4.setText("This is Register Page");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gotoPayRollPage(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gotoPayRollPage
        // TODO add your handling code here:
        this.cardLayout.show(getContentPane(), "card2");
    }//GEN-LAST:event_gotoPayRollPage

    private void gotoRegisterPage(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gotoRegisterPage
        // TODO add your handling code here:
        this.cardLayout.show(getContentPane(), "card4");
    }//GEN-LAST:event_gotoRegisterPage

    private void LoginUsernameInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LoginUsernameInputFocusGained
        // TODO add your handling code here:
        if(LoginUsernameInput.getText().equals("Username") && isUsernamePlaceholder){
            LoginUsernameInput.setText(null);
            LoginUsernameInput.requestFocus();
            removePlaceholderStyle(LoginUsernameInput);
            isUsernamePlaceholder = false;
        }
    }//GEN-LAST:event_LoginUsernameInputFocusGained

    private void LoginPasswordInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LoginPasswordInputFocusGained
        // TODO add your handling code here:
        if(LoginPasswordInput.getText().equals("Password") && isPasswordPlaceholder){
            LoginPasswordInput.setText(null);
            LoginPasswordInput.requestFocus();
            if(!this.isShowPassword){
                LoginPasswordInput.setEchoChar('●');
            }
            removePlaceholderStyle(LoginPasswordInput);
            isPasswordPlaceholder = false;
        }
    }//GEN-LAST:event_LoginPasswordInputFocusGained

    private void ShowPasswordCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ShowPasswordCheckBoxStateChanged
        // TODO add your handling code here:
        isShowPassword = ShowPasswordCheckBox.isSelected();
        if(isShowPassword){
            LoginPasswordInput.setEchoChar('\u0000');
        }
        else if(LoginPasswordInput.getText().length() > 0 && !isPasswordPlaceholder){
            LoginPasswordInput.setEchoChar('●');
        }
    }//GEN-LAST:event_ShowPasswordCheckBoxStateChanged

    private void LoginUsernameInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LoginUsernameInputFocusLost
        // TODO add your handling code here:
        if(LoginUsernameInput.getText().length() == 0){
            LoginUsernameInput.setText("Username");
            addPlaceholderStyle(LoginUsernameInput);
            isUsernamePlaceholder = true;
        }
    }//GEN-LAST:event_LoginUsernameInputFocusLost

    private void LoginPasswordInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LoginPasswordInputFocusLost
        // TODO add your handling code here:
        if(LoginPasswordInput.getText().length() == 0){
            LoginPasswordInput.setText("Password");
            LoginPasswordInput.setEchoChar('\u0000');
            addPlaceholderStyle(LoginPasswordInput);
            isPasswordPlaceholder = true;
        }
    }//GEN-LAST:event_LoginPasswordInputFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(payrollGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payrollGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payrollGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payrollGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payrollGUI().setVisible(true);
            }
        });
        
         rmiinterface Obj;
        try {
            Obj = (rmiinterface)Naming.lookup("rmi://localhost:1040/AutomatedPayrollSystem");
            Obj.checkConnection();
        } catch (NotBoundException ex) {
            Logger.getLogger(payrollGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(payrollGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(payrollGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JButton LoginButton1;
    private javax.swing.JPanel LoginPage;
    private javax.swing.JPasswordField LoginPasswordInput;
    private javax.swing.JTextField LoginUsernameInput;
    private javax.swing.JCheckBox ShowPasswordCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
