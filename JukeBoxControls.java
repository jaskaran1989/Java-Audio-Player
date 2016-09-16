//********************************************************************
//  JukeBoxControls.java       Java Foundations
//
//  Represents the control panel for the juke box.
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.net.URL;

public class JukeBoxControls extends JPanel
{
   private JComboBox musicCombo;
   private JButton stopButton, playButton;
   private AudioClip[] music;
   private AudioClip current;

   //-----------------------------------------------------------------
   //  Sets up the GUI for the juke box.
   //-----------------------------------------------------------------
   public JukeBoxControls()
   {
      URL url1, url2, url3, url4, url5 ;
      url1 = url2 = url3 = url4 = url5 =  null;

      // Obtain and store the audio clips to play
      try
      {
         url1 = new URL ("file", "localhost", "sala.wav");
         url2 = new URL ("file", "localhost", "sala3.wav");
         url3 = new URL ("file", "localhost", "sala4.wav");
         url4 = new URL ("file", "localhost", "sala5.wav");
         url5 = new URL ("file", "localhost", "sala6.wav");

         ;
      }
      catch (Exception exception) {}

      music = new AudioClip[8];
      music[0] = null;  // Corresponds to "Make a Selection..."
      music[1] = JApplet.newAudioClip (url1);
      music[2] = JApplet.newAudioClip (url2);
      music[3] = JApplet.newAudioClip (url3);
      music[4] = JApplet.newAudioClip (url4);
      music[5] = JApplet.newAudioClip (url5);
      ;


      // Create the list of strings for the combo box options
      String[] musicNames = {"Make A Selection...", "song1",
                             "song3", "song4",
               "song5"};

      musicCombo = new JComboBox (musicNames);
      musicCombo.setBackground (Color.orange);

      //  Set up the buttons
      playButton = new JButton ("Play ", new ImageIcon ("play.gif"));
      playButton.setBackground (Color.pink);
      stopButton = new JButton ("Stop  ", new ImageIcon ("stop.gif"));
      stopButton.setBackground (Color.red);



      //  Set up this panel
      setPreferredSize (new Dimension (550, 400));
      setBackground (Color.white);
      add (musicCombo);
      add (playButton);
      add (stopButton);


      musicCombo.addActionListener (new ComboListener());
      stopButton.addActionListener (new ButtonListener());
      playButton.addActionListener (new ButtonListener());



      current = null;
   }

   //*****************************************************************
   //  Represents the action listener for the combo box.
   //*****************************************************************
   private class ComboListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Stops playing the current selection (if any) and resets
      //  the current selection to the one chosen.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
         if (current != null)
            current.stop();

         current = music[musicCombo.getSelectedIndex()];
      }
    }

   //*****************************************************************
   //  Represents the action listener for both control buttons.
   //*****************************************************************
   private class ButtonListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Stops the current selection (if any) in either case. If
      //  the play button was pressed, start playing it again.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
         if (current != null)
            current.stop();

         if (event.getSource() == playButton)
            if (current != null)
               current.play();
      }
   }
}
