package src;

import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

class FileItr implements Iterator{
    private Scanner itr;
    private int lineNum;
    private boolean flag;

    public FileItr (String fileName) {
        File toRead = new File(fileName);
        try {
            itr = new Scanner(toRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lineNum = 0;
        flag = true;
        itr.useDelimiter("\\s*\n\\s*");
    }

    public boolean hasNext() {
        return flag;
    }

    public String next() {
        String toRtn = itr.next();
        lineNum ++;
        if (!itr.hasNext()) {
            itr.close();
            flag = false;
        }
        return toRtn.trim();
    }
    
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }

    public int lineNumber() {
        return lineNum;
    }
}
