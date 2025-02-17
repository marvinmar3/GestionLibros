
package modelo;

/**
 *
 * @author marvin
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private String editorial;
    private String anioPub;

    public Libro(String isbn, String titulo, String autor,String genero, String editorial, String anioPub) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPub = anioPub;
        this.genero = genero;
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnioPub() {
        return anioPub;
    }

    public void setAnioPub(String anioPub) {
        this.anioPub = anioPub;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
   public String toString() {
        return "Libro [ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor +
                ", Género=" + genero + ", Editorial=" + editorial + ", Año=" + anioPub + "]";
    }
    
}

    