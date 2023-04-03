package com.example.instagram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instagram.ui.theme.ColorBLUE

@Composable
fun HomeScreen(
    navController: NavController
) {
    val stories = listOf(
        ImageWithText(
            image = painterResource(id = R.drawable.ic_profile_pic),
            text = "Your Story"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.philipp),
            text = "phillip"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.ic_virat),
            text = "viratKohli"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.ic_sachin),
            text = "sachin"
        ),
        ImageWithText(
            image = painterResource(id = R.drawable.ic_chhetri),
            text = "chhetri"
        )
    )
    val feeds = listOf(
        FeedItemContainer(
            userImage = painterResource(id = R.drawable.ic_chhetri),
            userName = "chhetri",
            feedImage = painterResource(id = R.drawable.ic_chhetri),
            description = "Liked by Sachin and 1131 others"
        ),
        FeedItemContainer(
            userImage = painterResource(id = R.drawable.ic_virat),
            userName = "iAmKohli",
            feedImage = painterResource(id = R.drawable.ic_virat),
            description = "Liked by Anushka and 10.5M others"
        ),
        FeedItemContainer(
            userImage = painterResource(id = R.drawable.ic_profile_pic),
            userName = "signor_da_vinci",
            feedImage = painterResource(id = R.drawable.ic_profile_pic),
            description = "Liked by chhetri and 315 others"
        ),
        FeedItemContainer(
            userImage = painterResource(id = R.drawable.philipp),
            userName = "philipp",
            feedImage = painterResource(id = R.drawable.philipp),
            description = "Liked by Vivek and 131 others"
        )
    )
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Instagram", color = ColorBLUE,
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
                )
                RoundImage(
                    image = painterResource(id = R.drawable.ic_profile_pic),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navController.navigate(Screens.Profile)
                        }
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                items(stories.size) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(end = 15.dp)
                    ) {
                        RoundImage(image = stories[it].image, modifier = Modifier.size(70.dp))
                        Text(
                            text = stories[it].text,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
        items(feeds.size) {
            FeedItem(
                feeds[it],
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun FeedItem(
    feed: FeedItemContainer,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundImage(feed.userImage, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = feed.userName)
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
        Icon(
            painter = feed.feedImage, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            tint = Color.Unspecified
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.TwoTone.Favorite, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.TwoTone.Send, contentDescription = null)
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.TwoTone.Add, contentDescription = null)
        }
        Text(
            text = feed.description,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

data class FeedItemContainer(
    val userImage: Painter,
    val userName: String,
    val feedImage: Painter,
    val description: String
)