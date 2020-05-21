package br.com.estudo.dao;

import br.com.estudo.entity.Document;

public class DocumentDAO extends GenericDAO<Document> {

    public DocumentDAO() {
        super(Document.class);
    }
}
