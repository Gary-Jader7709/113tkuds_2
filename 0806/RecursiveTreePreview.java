import java.util.*;

public class RecursiveTreePreview {
    public static void main(String[] args) {
        FileNode root = new FileNode("root");
        root.add(new FileNode("file1.txt"));
        FileNode folder = new FileNode("folder");
        folder.add(new FileNode("file2.txt"));
        folder.add(new FileNode("file3.txt"));
        root.add(folder);
        System.out.println("Total files: " + countFiles(root));
    }

    static class FileNode {
        String name;
        List<FileNode> children = new ArrayList<>();

        FileNode(String name) {
            this.name = name;
        }

        void add(FileNode child) {
            children.add(child);
        }
    }

    public static int countFiles(FileNode node) {
        if (node.children.isEmpty())
            return 1;
        int sum = 0;
        for (FileNode child : node.children)
            sum += countFiles(child);
        return sum;
    }
}
