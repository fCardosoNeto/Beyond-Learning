package net.codejava.upload.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 512, nullable = false, unique= true) // tamanho do nome, (não pode ser conhecido), o nome é único
	private String name;
	
	private long size;
	
	@Column(name = "upload_time") // nome da coluna 
	private Date uploadTime;
	
	private byte[] content; // armazena o tamanho do arquivo
	
	public Document(Long id, String name, long size) { // mostrar os arquivos na tela 
		this.id = id;
		this.name = name;
		this.size = size;
	}

	public Document() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	

}
