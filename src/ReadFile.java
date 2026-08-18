package src;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {

    private int pmList[] = new int[800];

    public void selectAndReadFile(Component com) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(com);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) continue;
                    String text[] = trimmed.split("\\s+");
                    
                    
                    for(String el : text){

                        try {
                            int pmValue = Integer.parseInt(el);
                            if (pmValue >= 0 && pmValue <= 250) {
                                pmList[i] = pmValue;
                            }else{
                                pmList[i] = -1;
                            }
                            
                                
                        } catch (NumberFormatException e) {
                             pmList[i] = -1;
                        }
                        i++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public int[] getPM(){
        return this.pmList;
    }
}