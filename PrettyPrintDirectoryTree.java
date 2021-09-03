import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import static java.util.Objects.nonNull;

public class PrettyPrintDirectoryTree {

    public static void main(String[] args) {
        Path path = Path.of("./");

        String tree = getDirectoryTree(path.toFile());
        System.out.println(tree);
    }

    public static String getDirectoryTree(File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Not a directory!");
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        getDirectoryTree(folder, indent, sb);
        return sb.toString();
    }

    private static void getDirectoryTree(File directory, int indent, StringBuilder sb) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a directory!");
        }

        addBranch(directory.getName(), indent, sb);

        File[] files = directory.listFiles();

        if (nonNull(files)) {
            Arrays.sort(files);
            for (File file : files) {
                if (file.isDirectory()) {
                    getDirectoryTree(file, indent + 1, sb);
                } else {
                    addLeaf(file.getName(), indent, sb);
                }
            }
        }
    }

    private static void addLeaf(String name, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent + 1));
        sb.append("+-----------");
        sb.append(name);
        sb.append("\n");
    }

    private static void addBranch(String name, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+-----------");
        sb.append(name);
        sb.append("/");
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        return "|           ".repeat(Math.max(0, indent));
    }
}
