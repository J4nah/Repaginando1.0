package ApiGoogle;

import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import java.util.List;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String apiKey = "AIzaSyCPuXHJGXQTjE1piHYWn7lj2cTLAWSNoeQ";
        BookService bookService;

        try {
            bookService = new BookService(apiKey);
            Volumes volumes = bookService.search(JOptionPane.showInputDialog("Nome do livro"));

            StringBuilder output = new StringBuilder();

            if (volumes.getItems() != null && !volumes.getItems().isEmpty()) {
                for (Volume volume : volumes.getItems()) {
                    output.append("Tiulo: ").append(volume.getVolumeInfo().getTitle()).append("\n");

                    Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();

                    if (volumeInfo != null) {
                        List<Volume.VolumeInfo.IndustryIdentifiers> industryIdentifiers = volumeInfo.getIndustryIdentifiers();

                        if (industryIdentifiers != null) {
                            industryIdentifiers.forEach(industryIdentifier -> {
                                if ("ISBN_10".equalsIgnoreCase(industryIdentifier.getType())) {
                                    output.append("ISBN-10: ").append(industryIdentifier.getIdentifier()).append("\n");
                                } else if ("ISBN_13".equalsIgnoreCase(industryIdentifier.getType())) {
                                    output.append("ISBN-13: ").append(industryIdentifier.getIdentifier()).append("\n");
                                }
                            });
                        }
                    }

                    List<String> categories = volumeInfo.getCategories();
                    if (categories != null && !categories.isEmpty()) {
                        output.append("Gênero/Categoria: ").append(String.join(", ", categories)).append("\n");
                    }

                    if (volume.getVolumeInfo().getAuthors() != null) {
                        output.append("Autores(as): ").append(String.join(", ", volume.getVolumeInfo().getAuthors())).append("\n\n");
                    }

                    String publisher = volumeInfo.getPublisher();
                    if (publisher != null && !publisher.trim().isEmpty()) {
                        output.append("Editora: ").append(publisher).append("\n\n");
                    } else {
                        output.append("Editora: Não informada.\n\n");
                    }

                    String description = volume.getVolumeInfo().getDescription();
                    if (description != null) {
                        output.append("Descrição: ").append(description).append("\n\n");
                    }

                    String publishedDate = volumeInfo.getPublishedDate();
                    if (publishedDate != null) {
                        if (publishedDate.length() >= 4) {
                            output.append("Ano de Publicação: ").append(publishedDate.substring(0, 4)).append("\n");
                        } else {
                            output.append("Ano de Publicação: ").append(publishedDate).append("\n\n");
                        }
                    }

                    Integer pageCount = volumeInfo.getPageCount();
                    if (pageCount != null) {
                        if (pageCount == 0) {
                            output.append("Numero de páginas: Não informado.\n\n");
                        } else {
                            output.append("Numero de páginas: ").append(pageCount).append("\n\n");
                        }
                    }
                    if (volume.getVolumeInfo().getImageLinks() != null && volume.getVolumeInfo().getImageLinks().getThumbnail() != null) {
                        output.append("Imagem da capa: ").append(volume.getVolumeInfo().getImageLinks().getThumbnail()).append("\n\n");
                    }

                    output.append("------------------------------------------------\n");
                }
            } else {
                output.append("Livro não encontrado.");
            }

            showScrollableMessage(output.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showScrollableMessage(String longText) {
        JTextArea textArea = new JTextArea(20, 60);
        textArea.setText(longText);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Informações do Livro", JOptionPane.INFORMATION_MESSAGE);
    }
}
