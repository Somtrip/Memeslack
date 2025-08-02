package com.project.Xmeme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Xmeme.data.MemesEntity;
import com.project.Xmeme.exchange.GetMemeRequest;
import com.project.Xmeme.exchange.GetMemesResponse;
import com.project.Xmeme.service.MemesService;

@Primary
@RestController
public class MemesController {
    @Autowired
    private MemesService memesService;



    @PostMapping("/memes")
    public ResponseEntity<GetMemeRequest> createMeme(@RequestBody MemesEntity meme){
        System.out.println(meme.getName() + " " + meme.getCaption());
        if(meme == null || meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null){
            return ResponseEntity.status(400).build();
        } else if(memesService.isMemeAvailable(meme)){
            return ResponseEntity.status(409).build();
        } else {
            GetMemeRequest newMemeId = memesService.createMeme(meme);
            return ResponseEntity.ok().body(newMemeId);
        }
    }

    @GetMapping("/memes")
    public ResponseEntity<List<GetMemesResponse>> getTopMemes(){
        List<GetMemesResponse> memes = memesService.getMemes();
        return ResponseEntity.ok().body(memes);
    }

     @GetMapping("/memes/{id}")
    public ResponseEntity<GetMemesResponse> getMemeById(@PathVariable String id){
        MemesEntity meme = memesService.getMeme(id);
        if(meme != null){
            GetMemesResponse getMeme = new GetMemesResponse(
                meme.getId(),           // <-- Add id here
                meme.getName(),
                meme.getCaption(),
                meme.getUrl()
            );
            return ResponseEntity.ok().body(getMeme);
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @DeleteMapping("/memes/{id}")
    public ResponseEntity<Void> deleteMemeById(@PathVariable String id) {
        boolean deleted = memesService.deleteMeme(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
