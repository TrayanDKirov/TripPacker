package bg.sofia.uni.fmi.tdkirov.trippacker;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.GroupToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.ItemToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingGroupRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingItemRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.TripLuggageRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final GroupToPackRepository groupToPackRepository;
    private final ItemToPackRepository itemToPackRepository;
    private final TripLuggageRepository tripLuggageRepository;
    private final PackingGroupRepository packingGroupRepository;
    private final PackingItemRepository packingItemRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository,
                      GroupToPackRepository groupToPackRepository,
                      ItemToPackRepository itemToPackRepository,
                      TripLuggageRepository tripLuggageRepository,
                      PackingGroupRepository packingGroupRepository,
                      PackingItemRepository packingItemRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.groupToPackRepository = groupToPackRepository;
        this.itemToPackRepository = itemToPackRepository;
        this.tripLuggageRepository = tripLuggageRepository;
        this.packingGroupRepository = packingGroupRepository;
        this.packingItemRepository = packingItemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (userRepository.existsByUsername("ivan")) {
            return;
        }

        User ivan = userRepository.save(new User("ivan", passwordEncoder.encode("password123")));

        GroupToPack toiletries = saveGroup("Toiletries", ivan);
        List<ItemToPack> toiletryItems = List.of(
            saveItem("Toothbrush", 1, ivan, toiletries),
            saveItem("Toothpaste", 1, ivan, toiletries),
            saveItem("Shampoo", 1, ivan, toiletries),
            saveItem("Soap", 1, ivan, toiletries)
        );

        GroupToPack laptop = saveGroup("Laptop", ivan);
        List<ItemToPack> laptopItems = List.of(
            saveItem("Laptop", 1, ivan, laptop),
            saveItem("Laptop Charger", 1, ivan, laptop),
            saveItem("Mouse", 1, ivan, laptop),
            saveItem("Headphones", 1, ivan, laptop)
        );

        GroupToPack clothes2Days = saveGroup("Clothes for 2 Days", ivan);
        List<ItemToPack> clothes2DaysItems = List.of(
            saveItem("T-Shirt", 2, ivan, clothes2Days),
            saveItem("Pants", 2, ivan, clothes2Days),
            saveItem("Socks", 2, ivan, clothes2Days),
            saveItem("Underwear", 2, ivan, clothes2Days)
        );

        GroupToPack clothes7Days = saveGroup("Clothes for 7 Days", ivan);
        List<ItemToPack> clothes7DaysItems = List.of(
            saveItem("T-Shirt", 7, ivan, clothes7Days),
            saveItem("Pants", 7, ivan, clothes7Days),
            saveItem("Socks", 7, ivan, clothes7Days),
            saveItem("Underwear", 7, ivan, clothes7Days)
        );

        TripLuggage weekendTrip = tripLuggageRepository.save(new TripLuggage("Weekend Trip", ivan));
        addPackingGroup(clothes2Days, clothes2DaysItems, weekendTrip, ivan);
        addPackingGroup(toiletries, toiletryItems, weekendTrip, ivan);

        TripLuggage weekTrip = tripLuggageRepository.save(new TripLuggage("Week Trip", ivan));
        addPackingGroup(toiletries, toiletryItems, weekTrip, ivan);
        addPackingGroup(clothes7Days, clothes7DaysItems, weekTrip, ivan);
        addPackingGroup(laptop, laptopItems, weekTrip, ivan);
    }

    private GroupToPack saveGroup(String name, User user) {
        GroupToPack group = new GroupToPack(name, null);
        group.setCreatedBy(user);
        return groupToPackRepository.save(group);
    }

    private ItemToPack saveItem(String name, int quantity, User user, GroupToPack group) {
        return itemToPackRepository.save(new ItemToPack(name, quantity, user, group, null));
    }

    private void addPackingGroup(GroupToPack template, List<ItemToPack> templateItems,
                                  TripLuggage trip, User user) {
        PackingGroup packingGroup = new PackingGroup(new LinkedHashSet<>(), template, trip, user);
        PackingGroup saved = packingGroupRepository.save(packingGroup);

        for (ItemToPack item : templateItems) {
            packingItemRepository.save(new PackingItem(item, null, saved, user));
        }
    }
}