import org.lenskit.util.collections.LongUtils;
import org.lenskit.basic.AbstractItemRecommender;
import java.util.Random;
import it.unimi.dsi.fastutil.longs.*;
import org.lenskit.api.ResultList;
import javax.inject.Inject;
import javax.annotation.Nullable;
import it.unimi.dsi.fastutil.longs.LongLists.EmptyList;
import java.util.*;
import org.lenskit.results.Results;
import org.lenskit.api.Result;
import java.lang.Long;
import java.lang.Double;
import java.lang.Override;
import javax.annotation.Nonnull;
import org.lenskit.data.dao.ItemDAO;
import org.lenskit.data.dao.UserEventDAO;
import org.lenskit.data.events.Event;
import org.lenskit.data.history.UserHistory;


public class RandomItemRecommender extends AbstractItemRecommender
{

    public Random random;
    protected final UserEventDAO userEventDAO;
    protected final ItemDAO itemDAO;

    @Inject
    public RandomItemRecommender(UserEventDAO uedao, ItemDAO idao, Random random){
        this.random = random;
        userEventDAO = uedao;
        itemDAO = idao;
    }

    protected List<Long> recommend(long user, int n, LongSet candidates, LongSet exclude) {
        return recommendWithDetails(user, n, candidates, exclude).idList();
    }

    @Override
    public ResultList recommendWithDetails(long user, int n, Set<Long> candidates, Set<Long> exclude) {
        return recommendWithDetails(user, n, LongUtils.asLongSet(candidates), LongUtils.asLongSet(exclude));
    }

    protected ResultList recommendWithDetails(long user, int n, LongSet candidates, LongSet exclude)
    {
        LongList list = new LongArrayList();
        List<Result> rlist = new ArrayList<Result>();
        if (n > 0)
            candidates = getEffectiveCandidates(user, candidates, exclude, n);
        for ( LongIterator i = candidates.iterator(); i.hasNext();) {
                    long item = i.nextLong();
                   list.add(new Long(item));
            }
        list = LongLists.shuffle(list,random);

        for(LongIterator i = list.iterator(); i.hasNext();){
            long item = i.nextLong();
            rlist.add(Results.create(item,Double.NaN));

        }

        return Results.newResultList(rlist);
    }

    private LongSet getEffectiveCandidates(long user, LongSet candidates, LongSet exclude, int n) {
        if (candidates == null) {
            candidates = getPredictableItems(user);
        }
        if (exclude == null) {
            exclude = getDefaultExcludes(user);
        }
        if (!exclude.isEmpty()) {
            candidates = LongUtils.randomSubset(candidates, n, exclude, random);
        }
        return candidates;
    }

    protected LongSet getPredictableItems(long user) {
        return itemDAO.getItemIds();
    }

    protected LongSet getDefaultExcludes(long user) {
        return getDefaultExcludes(userEventDAO.getEventsForUser(user));
    }

    protected LongSet getDefaultExcludes(@Nullable UserHistory<? extends Event> user) {
        if (user == null) {
            return LongSets.EMPTY_SET;
        } else {
            return user.itemSet();
        }
    }

}
