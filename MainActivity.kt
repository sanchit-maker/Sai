package com.huntersystem.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class Quest(
    val title: String,
    val subject: String,
    val minutes: Int,
    val xp: Int,
    val reason: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HunterApp() }
    }
}

@Composable
fun HunterApp() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        var selected by remember { mutableStateOf(0) }
        val tabs = listOf("TODAY", "SYLLABUS", "TEST RADAR", "STATS")

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("HUNTER SYSTEM")
                            Text(
                                "Become stronger every day.",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    tabs.forEachIndexed { i, label ->
                        NavigationBarItem(
                            selected = selected == i,
                            onClick = { selected = i },
                            icon = { Text(label.take(1)) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        ) { padding ->
            Box(Modifier.padding(padding).fillMaxSize()) {
                when (selected) {
                    0 -> TodayScreen()
                    1 -> SyllabusScreen()
                    2 -> TestRadarScreen()
                    3 -> StatsScreen()
                }
            }
        }
    }
}

@Composable
fun TodayScreen() {
    val quests = remember {
        listOf(
            Quest("Laws of Motion: concepts + examples", "Physics", 60, 120, "MHT-CET Minor-4 is approaching"),
            Quest("Chemical Bonding: revise weak areas", "Chemistry", 50, 100, "Test syllabus + weak-topic evidence"),
            Quest("Sequence & Series: timed problems", "Maths", 60, 140, "Upcoming test + problem-solving practice"),
            Quest("Error-log repair: 10 old mistakes", "Mixed", 30, 90, "Repeated mistakes need repair")
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("TODAY'S HUNT", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(6.dp))
                    Text("The system generates your work. You don't need to type today's tasks.")
                    Spacer(Modifier.height(12.dp))
                    LinearProgressIndicator(progress = { 0.18f }, Modifier.fillMaxWidth())
                    Spacer(Modifier.height(6.dp))
                    Text("Daily progress: 18%")
                }
            }
        }

        items(quests) { q ->
            QuestCard(q)
        }

        item {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("START FOCUS SESSION")
            }
        }
    }
}

@Composable
fun QuestCard(q: Quest) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(q.title, style = MaterialTheme.typography.titleMedium)
            Text("${q.subject} • ${q.minutes} min • +${q.xp} XP")
            Spacer(Modifier.height(6.dp))
            Text(q.reason, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(10.dp))
            OutlinedButton(onClick = { }) {
                Text("START QUEST")
            }
        }
    }
}

@Composable
fun SyllabusScreen() {
    val topics = listOf(
        "Physics — Laws of Motion" to 52,
        "Chemistry — Chemical Bonding" to 64,
        "Maths — Sequence & Series" to 41,
        "Maths — Complex Number" to 58,
        "Physics — Motion in a Plane" to 72
    )

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("SYLLABUS", style = MaterialTheme.typography.headlineSmall)
            Text("Mastery is based on evidence, not a manual 'done' button.")
        }
        items(topics) { (name, mastery) ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(name, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { mastery / 100f },
                        Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("Mastery: $mastery%")
                }
            }
        }
    }
}

@Composable
fun TestRadarScreen() {
    val tests = listOf(
        "20 Sep 2026 — MHT-CET Minor-4" to "Physics: Laws of Motion Full • Chemistry: Chemical Bonding, Redox • Maths: Sequence & Series",
        "27 Sep 2026 — JEE Main Minor-2" to "Physics: Laws of Motion Full • Chemistry: Chemical Bonding, Redox, Analytical Techniques, IUPAC • Maths: Complex Number, Sequence & Series",
        "11 Oct 2026 — MHT-CET Minor-5" to "Physics: Optics • Chemistry: IUPAC, Isomerism • Maths: Straight Line"
    )

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("TEST RADAR", style = MaterialTheme.typography.headlineSmall)
            Text("Upcoming tests influence priorities without stopping long-term progression.")
        }
        items(tests) { (date, syllabus) ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(date, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(6.dp))
                    Text(syllabus)
                    Spacer(Modifier.height(10.dp))
                    Text("Readiness will use mastery, accuracy, timed performance, revision and mistakes.")
                }
            }
        }
    }
}

@Composable
fun StatsScreen() {
    val stats = listOf(
        "Knowledge" to 48,
        "Problem Solving" to 39,
        "Focus" to 55,
        "Discipline" to 44,
        "Consistency" to 31
    )

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("HUNTER STATS", style = MaterialTheme.typography.headlineSmall)
            Text("Stats represent measured behavior and performance; they are not a measure of intelligence.")
        }
        items(stats) { (name, value) ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(name)
                        Text("$value")
                    }
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { value / 100f },
                        Modifier.fillMaxWidth()
                    )
                }
            }
        }
        item {
            Spacer(Modifier.height(8.dp))
            Text("LEVEL 7", style = MaterialTheme.typography.headlineMedium)
            Text("XP is earned from productive work: questions, tests, verified revision and focused sessions.")
        }
    }
}
