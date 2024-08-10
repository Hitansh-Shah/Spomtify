
package com.ApiTesting;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jmusixmatch.MusixMatchException;
import org.json.simple.parser.ParseException;


public class Gui extends javax.swing.JFrame {
    
    
    
    
    public Gui() {
        initComponents();
        Panel2.setVisible(false);
        EmptyHistoryLabel.setVisible(false);
        HistoryButton.setVisible(false);
        ClearHistoryButton.setVisible(false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        int hour = date.getHours();
        if(hour>=0 && hour<12) {
            Greeter.setText("Good morning");
        } else if(hour>=12 && hour<18) {
            Greeter.setText("Good afternoon");
        } else {
            Greeter.setText("Good evening");
        }

//        File font_file = new File("devanagari_font.ttf");
        File font_file = new File("NotoSans-SemiBold.ttf");
        Font myfont = null;
        try {
            myfont = Font.createFont(Font.TRUETYPE_FONT, font_file);

            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(myfont);
            myfont = myfont.deriveFont(18f);
            setFont(myfont);
            System.out.println("hehehe");
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }


        int[] usedArr = new int[6];

        try {
            HistoryTracker.getHistory();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }


        int count = 0;
        for(count = 0;count<6;count++) {
            try {
                int n = RandomNumber.getRandomNumber();
                while(RandomNumber.isUsed(n, usedArr)) {
                    n = RandomNumber.getRandomNumber();
                }
                
                usedArr[count] = n;
                
                Color color = RandomNumber.getColor(n);
                int histSize = HistoryTracker.giveSizeOfHistory();
                switch(count + 1) {
                    case 1:
                        if(histSize < 1) {
                            R1.setVisible(false);
                            EmptyHistoryLabel.setVisible(true);
                        } else {
                            R1.setBackground(color);


                            Font finalFont = myfont;
                            R1.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel1.getText());
                                    TrackField.setText(TNLabel1.getText());
                                    Main.artistName = ANLabel1.getText();
                                    Main.trackName = TNLabel1.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);
                                    Display.setText(Lyrics.lyrics);
//                                    Display.setFont(new Font(font));
                                    Display.setFont(finalFont);
















                                }
                            });
                            
                            
                            
                            ANLabel1.setText(HistoryTracker.giveInfo(histSize - 1, "Artist Name"));
                            TNLabel1.setText(HistoryTracker.giveInfo(histSize - 1, "Track Name"));
                        }
                        break;
                    case 2:
                        if(histSize < 2) {
                            R2.setVisible(false);
                        } else {
                            R2.setBackground(color);
                            
                            
                            
                            R2.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel2.getText());
                                    TrackField.setText(TNLabel2.getText());
                                    Main.artistName = ANLabel2.getText();
                                    Main.trackName = TNLabel2.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);

                                    Display.setText(Lyrics.lyrics);
                                    
                                    
                                }
                            });
                            
                            
                            ANLabel2.setText(HistoryTracker.giveInfo(histSize - 2, "Artist Name"));
                            TNLabel2.setText(HistoryTracker.giveInfo(histSize - 2, "Track Name"));
                        }
                        break;
                    case 3:
                        if(histSize < 3) {
                            R3.setVisible(false);
                        } else {
                            R3.setBackground(color);
                            
                            
                            R3.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel3.getText());
                                    TrackField.setText(TNLabel3.getText());
                                    Main.artistName = ANLabel3.getText();
                                    Main.trackName = TNLabel3.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);

                                    Display.setText(Lyrics.lyrics);
                                    
                                    
                                }
                            });
                            
                            
                            
                            ANLabel3.setText(HistoryTracker.giveInfo(histSize - 3, "Artist Name"));
                            TNLabel3.setText(HistoryTracker.giveInfo(histSize - 3, "Track Name"));
                            }
                        break;
                    case 4:
                        if(histSize < 4) {
                            R4.setVisible(false);
                        } else {
                            R4.setBackground(color);
                            
                            
                            R4.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel4.getText());
                                    TrackField.setText(TNLabel4.getText());
                                    Main.artistName = ANLabel4.getText();
                                    Main.trackName = TNLabel4.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);

                                    Display.setText(Lyrics.lyrics);
                                    
                                    
                                }
                            });
                            
                            
                            
                            ANLabel4.setText(HistoryTracker.giveInfo(histSize - 4, "Artist Name"));
                            TNLabel4.setText(HistoryTracker.giveInfo(histSize - 4, "Track Name"));
                            }
                        break;
                    case 5:
                        if(histSize < 5) {
                            R5.setVisible(false);
                        } else {
                            
                            
                            
                            
                            R5.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel5.getText());
                                    TrackField.setText(TNLabel5.getText());
                                    Main.artistName = ANLabel5.getText();
                                    Main.trackName = TNLabel5.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);

                                    Display.setText(Lyrics.lyrics);
                                    
                                    
                                }
                            });
                            
                            
                            
                            R5.setBackground(color);
                            ANLabel5.setText(HistoryTracker.giveInfo(histSize - 5, "Artist Name"));
                            TNLabel5.setText(HistoryTracker.giveInfo(histSize - 5, "Track Name"));
                            }
                        break;
                    case 6:
                        if(histSize < 6) {
                            R6.setVisible(false);
                        } else {
                            R6.setBackground(color);
                            
                            
                            
                            R6.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Loading.setForeground(Color.WHITE);
                                    
                                    ArtistField.setText(ANLabel6.getText());
                                    TrackField.setText(TNLabel6.getText());
                                    Main.artistName = ANLabel6.getText();
                                    Main.trackName = TNLabel6.getText();
                                    Loading.setText("Loading...");
                                    Display.setText(null);
                                    AlbumField.setText(null);
                                }
                                
                                
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    
                                    
                                    try {
                                        Main.main();
                                    } catch (MusixMatchException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Loading.setText("");
                                    Panel1.setVisible(false);
                                    Panel2.setVisible(true);
                                    HistoryButton.setVisible(true);
                                    ClearHistoryButton.setVisible(true);
                                    Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
                                    Display.setBackground(color);
                                    AlbumField.setText(Main.albumName);

                                    Display.setText(Lyrics.lyrics);
                                    
                                    
                                }
                            });
                            
                            
                            
                            ANLabel6.setText(HistoryTracker.giveInfo(histSize - 6, "Artist Name"));
                            TNLabel6.setText(HistoryTracker.giveInfo(histSize - 6, "Track Name"));
                            }
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }




    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        Greeter = new javax.swing.JLabel();
        HistoryButton = new javax.swing.JLabel();
        SearchButton = new javax.swing.JLabel();
        ArtistField = new javax.swing.JTextField();
        TrackField = new javax.swing.JTextField();
        Loading = new javax.swing.JLabel();
        ClearHistoryButton = new javax.swing.JButton();
        Panel2 = new javax.swing.JPanel();
        AlbumLabel = new javax.swing.JLabel();
        AlbumField = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Display = new javax.swing.JTextArea();
        LyricsButton = new javax.swing.JButton();
        AlbumTracksButton = new javax.swing.JButton();
        ArtistAlbumsButton = new javax.swing.JButton();
        Panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        R1 = new javax.swing.JPanel();
        TNLabel1 = new javax.swing.JLabel();
        ANLabel1 = new javax.swing.JLabel();
        R2 = new javax.swing.JPanel();
        ANLabel2 = new javax.swing.JLabel();
        TNLabel2 = new javax.swing.JLabel();
        R3 = new javax.swing.JPanel();
        TNLabel3 = new javax.swing.JLabel();
        ANLabel3 = new javax.swing.JLabel();
        R4 = new javax.swing.JPanel();
        TNLabel4 = new javax.swing.JLabel();
        ANLabel4 = new javax.swing.JLabel();
        R5 = new javax.swing.JPanel();
        TNLabel5 = new javax.swing.JLabel();
        ANLabel5 = new javax.swing.JLabel();
        R6 = new javax.swing.JPanel();
        TNLabel6 = new javax.swing.JLabel();
        ANLabel6 = new javax.swing.JLabel();
        EmptyHistoryLabel = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spomtify");
        setBackground(new java.awt.Color(102, 102, 255));
        setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 0, 0));
        kGradientPanel1.setkGradientFocus(120);
        kGradientPanel1.setkStartColor(new java.awt.Color(81, 30, 147));

        Greeter.setBackground(new java.awt.Color(255, 255, 255));
        Greeter.setFont(new java.awt.Font("FreeSans", 1, 38)); // NOI18N
        Greeter.setForeground(new java.awt.Color(255, 255, 255));
        Greeter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Greeter.setText("Good Evening");
        Greeter.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        HistoryButton.setBackground(new java.awt.Color(0, 0, 0));
        HistoryButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        HistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        HistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/history (1).png"))); // NOI18N
        HistoryButton.setText("Search History");
        HistoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoryButtonMouseClicked(evt);
            }
        });

        SearchButton.setBackground(new java.awt.Color(0, 0, 0));
        SearchButton.setForeground(new java.awt.Color(255, 255, 255));
        SearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe (3).png"))); // NOI18N
        SearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SearchButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SearchButtonMouseReleased(evt);
            }
        });

        ArtistField.setBackground(new java.awt.Color(51, 51, 51));
        ArtistField.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        ArtistField.setForeground(new java.awt.Color(255, 255, 255));
        ArtistField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ArtistField.setText("Enter Artist name");
        ArtistField.setBorder(null);
        ArtistField.setCaretColor(new java.awt.Color(255, 255, 255));
        ArtistField.setCaretPosition(1);
        ArtistField.setSelectionColor(new java.awt.Color(0, 0, 0));
        ArtistField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ArtistFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ArtistFieldFocusLost(evt);
            }
        });
        ArtistField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArtistFieldActionPerformed(evt);
            }
        });

        TrackField.setBackground(new java.awt.Color(51, 51, 51));
        TrackField.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        TrackField.setForeground(new java.awt.Color(255, 255, 255));
        TrackField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TrackField.setText("Enter Track name");
        TrackField.setBorder(null);
        TrackField.setCaretColor(new java.awt.Color(255, 255, 255));
        TrackField.setSelectionColor(new java.awt.Color(0, 0, 0));
        TrackField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TrackFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TrackFieldFocusLost(evt);
            }
        });
        TrackField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrackFieldActionPerformed(evt);
            }
        });

        Loading.setBackground(new java.awt.Color(255, 255, 255));
        Loading.setForeground(new java.awt.Color(255, 255, 255));
        Loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ClearHistoryButton.setBackground(new java.awt.Color(0, 0, 0));
        ClearHistoryButton.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        ClearHistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearHistoryButton.setText("Clear Search History");
        ClearHistoryButton.setBorder(null);
        ClearHistoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClearHistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearHistoryButtonMouseClicked(evt);
            }
        });
        ClearHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearHistoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Greeter, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(Loading, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167)
                        .addComponent(ClearHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(TrackField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ArtistField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(HistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(HistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Greeter, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TrackField)
                                .addComponent(ArtistField))))
                    .addComponent(SearchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Loading, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel2.setBackground(new java.awt.Color(0, 0, 0));

        AlbumLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        AlbumLabel.setForeground(new java.awt.Color(255, 255, 255));
        AlbumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AlbumLabel.setText("Album Name:");

        AlbumField.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        AlbumField.setForeground(new java.awt.Color(255, 255, 255));

        Display.setBackground(new java.awt.Color(0, 0, 0));
        Display.setColumns(20);
        Display.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        Display.setForeground(new java.awt.Color(255, 255, 255));
        Display.setRows(5);
        jScrollPane1.setViewportView(Display);

        LyricsButton.setBackground(new java.awt.Color(0, 0, 0));
        LyricsButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        LyricsButton.setForeground(new java.awt.Color(255, 255, 255));
        LyricsButton.setText("Lyrics of this track");
        LyricsButton.setBorder(null);
        LyricsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LyricsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LyricsButtonMouseClicked(evt);
            }
        });
        LyricsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LyricsButtonActionPerformed(evt);
            }
        });

        AlbumTracksButton.setBackground(new java.awt.Color(0, 0, 0));
        AlbumTracksButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        AlbumTracksButton.setForeground(new java.awt.Color(255, 255, 255));
        AlbumTracksButton.setText("All tracks from this album");
        AlbumTracksButton.setBorder(null);
        AlbumTracksButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AlbumTracksButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlbumTracksButtonMouseClicked(evt);
            }
        });
        AlbumTracksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbumTracksButtonActionPerformed(evt);
            }
        });

        ArtistAlbumsButton.setBackground(new java.awt.Color(0, 0, 0));
        ArtistAlbumsButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        ArtistAlbumsButton.setForeground(new java.awt.Color(255, 255, 255));
        ArtistAlbumsButton.setText("More albums from this artist");
        ArtistAlbumsButton.setBorder(null);
        ArtistAlbumsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ArtistAlbumsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArtistAlbumsButtonMouseClicked(evt);
            }
        });
        ArtistAlbumsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArtistAlbumsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AlbumTracksButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArtistAlbumsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LyricsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                .addContainerGap(423, Short.MAX_VALUE)
                .addComponent(AlbumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AlbumField, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AlbumField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                        .addComponent(ArtistAlbumsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(AlbumTracksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(LyricsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
        );

        Panel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Recently Searched");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        R1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TNLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel1.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ANLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R1Layout = new javax.swing.GroupLayout(R1);
        R1.setLayout(R1Layout);
        R1Layout.setHorizontalGroup(
            R1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TNLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(ANLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R1Layout.setVerticalGroup(
            R1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(ANLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        R2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ANLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel2.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        TNLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel2.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R2Layout = new javax.swing.GroupLayout(R2);
        R2.setLayout(R2Layout);
        R2Layout.setHorizontalGroup(
            R2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ANLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(TNLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R2Layout.setVerticalGroup(
            R2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(ANLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        R3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TNLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel3.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ANLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel3.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R3Layout = new javax.swing.GroupLayout(R3);
        R3.setLayout(R3Layout);
        R3Layout.setHorizontalGroup(
            R3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TNLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(ANLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R3Layout.setVerticalGroup(
            R3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(ANLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        R4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TNLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel4.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ANLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel4.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R4Layout = new javax.swing.GroupLayout(R4);
        R4.setLayout(R4Layout);
        R4Layout.setHorizontalGroup(
            R4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TNLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(ANLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R4Layout.setVerticalGroup(
            R4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(ANLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        R5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TNLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel5.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ANLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel5.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R5Layout = new javax.swing.GroupLayout(R5);
        R5.setLayout(R5Layout);
        R5Layout.setHorizontalGroup(
            R5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TNLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(ANLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R5Layout.setVerticalGroup(
            R5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(ANLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        R6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TNLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        TNLabel6.setForeground(new java.awt.Color(255, 255, 255));
        TNLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ANLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        ANLabel6.setForeground(new java.awt.Color(255, 255, 255));
        ANLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout R6Layout = new javax.swing.GroupLayout(R6);
        R6.setLayout(R6Layout);
        R6Layout.setHorizontalGroup(
            R6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TNLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(ANLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        R6Layout.setVerticalGroup(
            R6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, R6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(ANLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TNLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        EmptyHistoryLabel.setBackground(new java.awt.Color(0, 0, 0));
        EmptyHistoryLabel.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        EmptyHistoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        EmptyHistoryLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        EmptyHistoryLabel.setText("No reecent searches!");
        EmptyHistoryLabel.setBorder(null);
        EmptyHistoryLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmptyHistoryLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmptyHistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmptyHistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ArtistFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArtistFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ArtistFieldActionPerformed

    private void ClearHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearHistoryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearHistoryButtonActionPerformed

    private void AlbumTracksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbumTracksButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlbumTracksButtonActionPerformed

    private void LyricsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LyricsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LyricsButtonActionPerformed

    private void ArtistAlbumsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArtistAlbumsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ArtistAlbumsButtonActionPerformed

    private void ArtistAlbumsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArtistAlbumsButtonMouseClicked
        // TODO add your handling code here:
        if(Artist_Albums.tracks2.toString().equals("")) {
            Display.setText("There are no other albums from this artist");
        } else {
             Display.setText(Artist_Albums.tracks2.toString());
        }
    }//GEN-LAST:event_ArtistAlbumsButtonMouseClicked

    private void AlbumTracksButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlbumTracksButtonMouseClicked

        Display.setText(Album_Tracks.album_tracks.toString());
    }//GEN-LAST:event_AlbumTracksButtonMouseClicked

    private void LyricsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LyricsButtonMouseClicked
        // TODO add your handling code here:
        Display.setText(Lyrics.lyrics);
    }//GEN-LAST:event_LyricsButtonMouseClicked

    private void ClearHistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearHistoryButtonMouseClicked
        try {
            // TODO add your handling code here:
            HistoryTracker.clearHistory();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        Display.setText("History Cleared");
    }//GEN-LAST:event_ClearHistoryButtonMouseClicked

//    private void SearchButtonMousePressed(java.awt.event.MouseEvent evt) {                                          
//        // TODO add your handling code here:
//        Loading.setText("Loading");
//        Display.setText(null);
//        AlbumField.setText(null);
//        Main.artistName = ArtistField.getText();
//        Main.trackName = TrackField.getText();
//    }                                         
//
//    private void SearchButtonMouseReleased(java.awt.event.MouseEvent evt) {                                           
//        // TODO add your handling code here:
//        try {
//            Main.main();
//        } catch (MusixMatchException ex) {
//            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Loading.setText("");
//        AlbumField.setText(Main.albumName);
//    }   
    
    
    private void HistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseClicked
        try {
            // TODO add your handling code here:
            HistoryTracker.getHistory();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(HistoryTracker.history2.toString().equals("")) {
            Display.setText("No searches yet");
        } else {
            Display.setText(HistoryTracker.history2.toString());
        }
    }//GEN-LAST:event_HistoryButtonMouseClicked

    private void SearchButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMousePressed
        // TODO add your handling code here:
        Loading.setForeground(Color.WHITE);
        Main.artistName = ArtistField.getText();
        Main.trackName = TrackField.getText();
        Loading.setText("Loading...");
        Display.setText(null);
        AlbumField.setText(null);
        
    }//GEN-LAST:event_SearchButtonMousePressed

    private void SearchButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseReleased
        // TODO add your handling code here:
        if("Enter Artist name".equals(ArtistField.getText()) || "Enter Track name".equals(TrackField.getText()) || "".equals(ArtistField.getText()) || "".equals(TrackField.getText())) {
            Loading.setForeground(Color.red);
            Loading.setText("Please enter artist and track info!!!");
        } else {
            try {
                Main.main();
            } catch (MusixMatchException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            Loading.setText("");
            Panel1.setVisible(false);
            Panel2.setVisible(true);
            HistoryButton.setVisible(true);
            ClearHistoryButton.setVisible(true);
            Color color = RandomNumber.getColor(RandomNumber.getRandomNumber());
            Display.setBackground(color);
            AlbumField.setText(Main.albumName);

            Display.setText(Lyrics.lyrics);
        }
    }//GEN-LAST:event_SearchButtonMouseReleased

    private void TrackFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrackFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrackFieldActionPerformed

    private void ArtistFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ArtistFieldFocusGained
        // TODO add your handling code here:
        if(ArtistField.getText().equals("Enter Artist name")) {
            ArtistField.setText("");
        }
    }//GEN-LAST:event_ArtistFieldFocusGained

    private void ArtistFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ArtistFieldFocusLost
        // TODO add your handling code here:
        if(ArtistField.getText().equals("")) {
            ArtistField.setText("Enter Artist name");
        }
    }//GEN-LAST:event_ArtistFieldFocusLost

    private void TrackFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TrackFieldFocusGained
        // TODO add your handling code here:
        if(TrackField.getText().equals("Enter Track name")) {
            TrackField.setText("");
        }
    }//GEN-LAST:event_TrackFieldFocusGained

    private void TrackFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TrackFieldFocusLost
        // TODO add your handling code here:
        if(TrackField.getText().equals("")) {
            TrackField.setText("Enter Track name");
        }
    }//GEN-LAST:event_TrackFieldFocusLost

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void EmptyHistoryLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmptyHistoryLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmptyHistoryLabelActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ANLabel1;
    private javax.swing.JLabel ANLabel2;
    private javax.swing.JLabel ANLabel3;
    private javax.swing.JLabel ANLabel4;
    private javax.swing.JLabel ANLabel5;
    private javax.swing.JLabel ANLabel6;
    private javax.swing.JLabel AlbumField;
    private static javax.swing.JLabel AlbumLabel;
    private javax.swing.JButton AlbumTracksButton;
    private javax.swing.JButton ArtistAlbumsButton;
    private javax.swing.JTextField ArtistField;
    private javax.swing.JButton ClearHistoryButton;
    private javax.swing.JTextArea Display;
    private javax.swing.JTextField EmptyHistoryLabel;
    private javax.swing.JLabel Greeter;
    private javax.swing.JLabel HistoryButton;
    private javax.swing.JLabel Loading;
    private javax.swing.JButton LyricsButton;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel R1;
    private javax.swing.JPanel R2;
    private javax.swing.JPanel R3;
    private javax.swing.JPanel R4;
    private javax.swing.JPanel R5;
    private javax.swing.JPanel R6;
    private javax.swing.JLabel SearchButton;
    private javax.swing.JLabel TNLabel1;
    private javax.swing.JLabel TNLabel2;
    private javax.swing.JLabel TNLabel3;
    private javax.swing.JLabel TNLabel4;
    private javax.swing.JLabel TNLabel5;
    private javax.swing.JLabel TNLabel6;
    private javax.swing.JTextField TrackField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
