package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.domain.Taco;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequestMapping(value = "/design", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;
//
//    @Autowired
//    EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recenta")
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacoList  = tacoRepository.findAll(pageRequest).getContent();
        CollectionModel<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacoList);
        tacoResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
         return tacoResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoResource> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            Taco taco = optTaco.get();
            TacoResource tacoResource = new TacoResourceAssembler().toModel(taco);
            return new ResponseEntity<>(tacoResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

}
