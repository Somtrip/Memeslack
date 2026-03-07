package com.project.Xmeme.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Xmeme.data.MemesEntity;
import com.project.Xmeme.exchange.CreateMemeResponse;
import com.project.Xmeme.exchange.MemeCreateRequest;
import com.project.Xmeme.exchange.GetMemesResponse;
import com.project.Xmeme.repository.MemesRepository;

@Service
public class MemesService {

    @Autowired
    private MemesRepository memesRepository;

    public CreateMemeResponse createMeme(MemeCreateRequest memeReq) {
        MemesEntity meme = new MemesEntity();
        meme.setName(memeReq.getName());
        meme.setCaption(memeReq.getCaption());
        meme.setUrl(memeReq.getUrl());
        meme.setCreatedAt(LocalDateTime.now());
        MemesEntity newMeme = memesRepository.save(meme);
        return new CreateMemeResponse(newMeme.getId());
    }

    public boolean isMemeAvailable(MemeCreateRequest memeReq) {
        if (!memesRepository.findByNameAndUrlAndCaption(memeReq.getName(), memeReq.getUrl(), memeReq.getCaption())
                .isEmpty()) {
            return true;
        }
        return false;
    }

    public MemesEntity getMeme(String id) {
        MemesEntity meme = memesRepository.findById(id).orElse(null);
        return meme;
    }

    public List<GetMemesResponse> getMemes() {
        List<MemesEntity> memes = memesRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MemesEntity::getId).reversed())
                .limit(100)
                .collect(Collectors.toList());

        List<GetMemesResponse> memesResponses = new ArrayList<>();
        for (MemesEntity meme : memes) {
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