package com.project.Xmeme.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Xmeme.data.MemesEntity;
import com.project.Xmeme.exchange.GetMemeRequest;
import com.project.Xmeme.exchange.GetMemesResponse;
import com.project.Xmeme.repository.MemesRepository;

@Service
public class MemesService {

    @Autowired
    private MemesRepository memesRepository;

    public GetMemeRequest createMeme(MemesEntity meme){
        LocalTime now = LocalTime.now();
        meme.setCreatedAt(now);
        MemesEntity newMeme = memesRepository.save(meme);
        GetMemeRequest response = new GetMemeRequest(newMeme.getId());
        return response;
    }

    public boolean isMemeAvailable(MemesEntity meme){
        if(!memesRepository.findByNameAndUrlAndCaption(meme.getName(), meme.getUrl(), meme.getCaption()).isEmpty()){
            return true;
        }
        return false;
    }

    public MemesEntity getMeme(String id){
        MemesEntity meme = memesRepository.findById(id).orElse(null);
        return meme;
    }
    public List<GetMemesResponse> getMemes(){
        List<MemesEntity> memes = memesRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MemesEntity::getId).reversed())
                .limit(100)
                .collect(Collectors.toList());

        List<GetMemesResponse> memesResponses = new ArrayList<>();
       for(MemesEntity meme : memes){
    GetMemesResponse newMeme = new GetMemesResponse();
    newMeme.setId(meme.getId()); // <-- Add this line
    newMeme.setName(meme.getName());
    newMeme.setUrl(meme.getUrl());
    newMeme.setCaption(meme.getCaption());
    memesResponses.add(newMeme);
}
        return memesResponses;
    }

    public boolean deleteMeme(String id) {
        if (memesRepository.existsById(id)) {
            memesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}