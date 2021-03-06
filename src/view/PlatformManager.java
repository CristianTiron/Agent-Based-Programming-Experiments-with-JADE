package view;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import misc.Logger;
import util.Utils;

public class PlatformManager extends JFrame {

  private JPanel contentPane;
  private JTextField textMusicSeeker;
  private JTextField textMusicProvider;
  private  ContainerController jadeContainer;
  /**
   * Create the frame.
   * @param jadeContainer 
   */
  public PlatformManager(ContainerController jadeContainr) {
    this.jadeContainer = jadeContainr;
    setTitle("Platform Manager");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 383, 178);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JButton btnMusicSeeker = new JButton("Add");
    btnMusicSeeker.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String agentName = textMusicSeeker.getText();
          if(Utils.isBlank(agentName)) { Logger.warn("Agent has no name!"); return; }
          textMusicSeeker.setText("");
          Logger.info("Agent %s is being created...", agentName);
          AgentController ac = jadeContainer.createNewAgent(agentName, "agent.MusicSeeker", null);
          ac.start();
        } catch (StaleProxyException ex) {
          Logger.error(ex, "Agent couldn't created.");
        }
      }
    });
    btnMusicSeeker.setBounds(261, 31, 117, 29);
    contentPane.add(btnMusicSeeker);
    
    JLabel lblMusicSeeker = new JLabel("Add new music seeker agent:");
    lblMusicSeeker.setBounds(6, 6, 211, 16);
    contentPane.add(lblMusicSeeker);
    
    JLabel lblMusicProvider = new JLabel("Name:");
    lblMusicProvider.setBounds(6, 36, 38, 16);
    contentPane.add(lblMusicProvider);
    
    textMusicSeeker = new JTextField();
    textMusicSeeker.setBounds(43, 30, 223, 28);
    contentPane.add(textMusicSeeker);
    textMusicSeeker.setColumns(10);
    
    JButton btnMusicProvider = new JButton("Add");
    btnMusicProvider.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String agentName = textMusicProvider.getText();
          if(Utils.isBlank(agentName)) { Logger.warn("Agent has no name!"); return; }
          textMusicProvider.setText("");
          Logger.info("Agent %s is being created...", agentName);
          AgentController ac = jadeContainer.createNewAgent(agentName, "agent.MusicProvider", null);
          ac.start();
        } catch (StaleProxyException ex) {
          Logger.error(ex, "Agent couldn't created.");
        }
      }
    });
    btnMusicProvider.setBounds(261, 115, 117, 29);
    contentPane.add(btnMusicProvider);
    
    JLabel label = new JLabel("Name:");
    label.setBounds(6, 120, 38, 16);
    contentPane.add(label);
    
    textMusicProvider = new JTextField();
    textMusicProvider.setColumns(10);
    textMusicProvider.setBounds(43, 114, 223, 28);
    contentPane.add(textMusicProvider);
    
    JLabel lblSistemeYeniMzik = new JLabel("Add new music provider agent:");
    lblSistemeYeniMzik.setBounds(6, 92, 211, 16);
    contentPane.add(lblSistemeYeniMzik);
  }
}
