package src;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadFile {

    private ArrayList<Integer> pmList = new ArrayList();

    public void selectAndReadFile(Component com) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        pmList.clear();

        int result = fileChooser.showOpenDialog(com);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) continue;
                    String[] text = line.split("[\\t ]", -1);

                    for (int j = 0; j < 40; j++) {
                        try {
                            if (text.length > j ) {
                                int pmValue = Integer.parseInt(text[j]);
                                if (pmValue >= 0 && pmValue <= 250) {
                                    pmList.add(pmValue);
                                }else{
                                    pmList.add(-1) ; 
                                }
                            }else{
                                pmList.add(-1) ; 
                            }

                        } catch (NumberFormatException e) {
                            pmList.add(-1) ;
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public ArrayList<Integer> getPM(){
        return this.pmList;
    }
}