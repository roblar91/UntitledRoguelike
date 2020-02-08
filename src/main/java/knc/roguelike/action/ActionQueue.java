/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.action;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ActionQueue {
    private ActionQueue() {
    }

    public static Queue<Action> actions = new LinkedBlockingQueue<>();
}

