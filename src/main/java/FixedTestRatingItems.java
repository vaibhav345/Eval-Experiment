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


/**Class that returns test items that have a fixed
 * number of ratings.
 */
public class FixedTestRatingItems {
    int totalRatingCount = 0;
    private final RatingSummary summary;
    private final LongSet items;

    public FixedTestRatingItems(RatingSummary summary, LongSet items){
        this.summary = summary;
        this.items = items;
    }

    public LongSet getFixedTestRatingItems(LongSet items, int n){
        LongIterator iter = LongIterators.asLongIterator(items.iterator());
        LongSet fixedTestRatingItems = null;

        while (iter.hasNext()){
            totalRatingCount = totalRatingCount + summary.getItemRatingCount(item);
        }

        while (iter.hasNext()){
            int itemRatingCount = 0;
            double weight = 0;

            itemRatingCount = summary.getItemRatingCount(item);
            if(itemRatingCount <= n){
                fixedTestRatingItems.add(item);
            }
            else{

            }
            //weight = 1 - (itemRatingCount / totalRatingCount);

        }

        return fixedTestRatingItems;
    }

}
