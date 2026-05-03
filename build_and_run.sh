#!/bin/bash
set -e

# Restaurant Management System - Build & Run

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

echo "🍽️  Restaurant Management System - Build & Run"
echo "================================================"

if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 21 or higher."
    exit 1
fi

echo "✓ Java found"

echo ""
echo "📦 Compiling project with Maven..."
./mvnw -q -DskipTests compile

echo ""
echo "🚀 Running application..."
echo ""
./mvnw -q javafx:run

echo ""
echo "👋 Application closed"
