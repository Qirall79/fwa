package fr.fortytwo.cinema.models;

public class Avatar {
    private long id;
    private long fileSize;
    private String fileName;
    private String mimeType;
    private long userId;

    public Avatar(long id, long fileSize, String fileName, String mimeType, long userId) {
        this.id = id;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "<a href='/cinema/images/%s'>%s</a>".formatted(fileName, fileName);
    }

    
}
